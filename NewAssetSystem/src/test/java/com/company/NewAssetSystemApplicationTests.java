package com.company;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import com.company.entity.Assets;
import com.company.entity.Employee;
import com.company.exceptions.DuplicateAsset;
import com.company.exceptions.InvalidAssetId;
import com.company.exceptions.InvalidEmployeeDetails;
import com.company.exceptions.InvalidEmployeeId;
import com.company.exceptions.InvalidOrderId;
import com.company.exceptions.NoAuthority;
import com.company.repository.AssetRepository;
import com.company.repository.EmployeeRepository;
import com.company.repository.OrderRepository;
import com.company.repository.UserRepository;
import com.company.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NewAssetSystemApplicationTests {
	
	@Autowired
	EmployeeService empService;
	
	@Autowired
	OrderRepository ordRepo;
	
	@Autowired
	AssetRepository assetRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	UserRepository userRepo;
	
	
	
	@Test
	@Transactional
    void saveEmployeeTest() throws InvalidAssetId, InvalidEmployeeId, NoAuthority, DuplicateAsset, InvalidEmployeeDetails {
	    Set<Assets>assets = null;
		Employee emp =new Employee();
		emp.setAssets(assets);
		emp.setFirstName("Akash");
		emp.setLastName("Hari");
		emp.setEmailId("akasha@gmail.com");
		emp.setManagerId(2L);
		emp.setUser(userRepo.getReferenceById(1));
		
		empService.addEmployee(1, emp);
		boolean actualResult = empRepo.existsById(emp.getId());
		Employee savedEmp = empRepo.getReferenceById(emp.getId());
		
		assertEquals(emp.getFirstName(),savedEmp.getFirstName());
		assertEquals(emp.getEmailId(),savedEmp.getEmailId());
		assertEquals(emp.getLastName(),savedEmp.getLastName());
		assertThat(actualResult).isTrue();
		empRepo.deleteById(emp.getId());
	}
	
	@Test
	@Transactional
	void deleteEmployeeTest() throws NoAuthority, InvalidEmployeeId {
		Set<Assets>assets = new HashSet<Assets>();
		Employee emp =new Employee();
		emp.setAssets(assets);
		emp.setFirstName("prasad");
		emp.setLastName("adser");
		emp.setEmailId("pa@cg.com");
		emp.setManagerId(2L);
		emp.setUser(userRepo.getReferenceById(1));
		
		empRepo.save(emp);
		empService.deleteEmployee(1, emp.getId());
		boolean actualResult2 = empRepo.existsById(emp.getId());
		
		assertThat(actualResult2).isFalse();
		
	}
	@Test
	@Transactional
	void updateEmployeeTest() throws InvalidEmployeeId, NoAuthority {
		Set<Assets>assets = null;
		Employee emp =new Employee();
		emp.setAssets(assets);
		emp.setFirstName("prasad");
		emp.setLastName("adser");
		emp.setEmailId("pa@cg.com");
		emp.setManagerId(2L);
		emp.setUser(userRepo.getReferenceById(1));
		
		//Changed employee manager
		empRepo.save(emp);
		Employee updatedEmp =new Employee();
		updatedEmp.setId(emp.getId());
		updatedEmp.setAssets(assets);
		updatedEmp.setFirstName("Prasad");
		updatedEmp.setLastName("adser");
		updatedEmp.setEmailId("pa@cg.com");
		updatedEmp.setManagerId(4L);
		updatedEmp.setUser(userRepo.getReferenceById(1));
		
		empService.updateEmployee(1,updatedEmp);
		
		long newManagerId =empRepo.getReferenceById(emp.getId()).getManagerId();
		
		assertEquals(4L, newManagerId);
		empRepo.delete(emp);
	}
	@Test
	@Transactional
	void addAssetTest() throws NoAuthority, DuplicateAsset, InvalidEmployeeId {
		Assets asset = new Assets();
		asset.setAssetName("Company Pen");
		
		empService.addAsset(1, asset);
		
		boolean isPresent = assetRepo.existsById(asset.getAssetId());
		
		assertThat(isPresent).isTrue();
		assetRepo.deleteById(asset.getAssetId());
	}
	@Test
	@Transactional
	void updateAssetTest() throws NoAuthority, InvalidAssetId, InvalidEmployeeId {
		Assets asset = new Assets();
		asset.setAssetName("Company Pen");
	    assetRepo.save(asset);
	    //Change pen To dairy
	    
	    Assets newAsset = new Assets();
	    newAsset.setAssetId(asset.getAssetId());
	    newAsset.setAssetName("Company Coffe Cup");
	    
	    empService.updateAsset(1,newAsset);
		
		boolean isChanged = assetRepo.getReferenceById(asset.getAssetId()).getAssetName().equals("Company Coffe Cup");
		
		assertThat(isChanged).isTrue();
		assetRepo.deleteById(asset.getAssetId());
	}
	
	@Test
	@Transactional
	void deleteAssetTest() throws NoAuthority, InvalidAssetId, InvalidEmployeeId{
		Assets asset = new Assets();
		asset.setAssetName("Company Pen");
	    assetRepo.save(asset);
	    
	    empService.deleteAsset(1, asset.getAssetId());
	    
	    boolean isPresent = assetRepo.existsById(asset.getAssetId());
	    assertThat(isPresent).isFalse();
	}
	@Test
	@Transactional
	void testDuplicateAssetIdShouldThrowDuplicateAsset() throws DuplicateAsset {
		assertThrows(DuplicateAsset.class, ()->{
			Assets asset = new Assets();
			asset.setAssetId(1);
			asset.setAssetName("Company pen");
			empService.addAsset(1, asset);
		});	
	}
	@Test
	@Transactional
	void testInvalidOrderIdShouldThrowInvalidOrderId() throws InvalidOrderId {
		assertThrows(InvalidOrderId.class, ()->{
			empService.allocateOrder(1, 66);
		});	
	}
	
	@Test
	@Transactional
	void testEmployeeTryToAddEmployeeThrowNoAuthority() throws NoAuthority {
		assertThrows(NoAuthority.class, ()->{
			Set<Assets>assets = null;
			Employee emp =new Employee();
			emp.setAssets(assets);
			emp.setFirstName("prasad");
			emp.setLastName("adser");
			emp.setEmailId("pa@cg.com");
			emp.setManagerId(2L);
			emp.setUser(userRepo.getReferenceById(1));
			
			empService.addEmployee(3, emp);//here employee role id 3 is employee Its only authority to Admin
		});	
	}
	
	@Test
	@Transactional
	void testInvalidEmployeeIdThrowInvalidEmployeeId() throws InvalidEmployeeId {
		assertThrows(InvalidEmployeeId.class, ()->{
			empService.raiseRequest(99, 23, 1);
		});	
	}
	
	
}
