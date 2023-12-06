package com.company.service;


import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.company.dto.EmployeeDto;
import com.company.dto.EmployeeDto2;
import com.company.dto.EmployeeRedux;
import com.company.entity.Assets;
import com.company.entity.Employee;
import com.company.entity.Order;
import com.company.entity.Ticket;
import com.company.exceptions.DuplicateAsset;
import com.company.exceptions.InvalidAssetId;
import com.company.exceptions.InvalidEmployeeDetails;
import com.company.exceptions.InvalidEmployeeId;
import com.company.exceptions.InvalidOrderId;
import com.company.exceptions.NoAssetsFound;
import com.company.exceptions.NoAuthority;
import com.company.exceptions.NoOrderFound;
import com.company.repository.AssetRepository;
import com.company.repository.EmployeeRepository;
import com.company.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	AssetRepository assetRepo;
	@Autowired
	EmployeeRepository empRepo;
	@Autowired
	OrderRepository ordRepo;

	
	final String NOT_YOUR_ORDER = "This is not Your order";
	final String NOT_HAVE_AUTHORITY = "You do not have Authority";
	final String INVALID_EMP_ID = "Invalid employee id";
	final String INVALID_ASSET_ID = "Invalid asset id";
    final String ORDER_NOT_EXIST = "Order Id does not exist";
	
	public enum status{ pending("Pending"),
		returns("Return"),allocate("Allocate"),release("Release"),reject("Reject");
		String value;
		status(String str){
			this.value =str;
		}
	}	
	

	@Override
	public String  raiseRequest(long id,long empId,long assetId) throws InvalidAssetId,InvalidEmployeeId ,NoAuthority, DuplicateAsset{
		 if(!empRepo.existsById(id)||!empRepo.existsById(empId)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
		 }
		 if(!assetRepo.existsById(assetId)) {
			 throw new InvalidAssetId(INVALID_ASSET_ID);
		 }
		 boolean b = empRepo.getReferenceById(empId).getAssets().contains(assetRepo.getReferenceById(assetId));
		 if(b){
			 throw new DuplicateAsset("You can not one asset item two times");
		 }
		 String ticket = null;
		 long level =empRepo.getReferenceById(id).getUser().getUserId();
		 if(level<3) {
			 empRepo.getReferenceById(empId).getAssets().add(assetRepo.getReferenceById(assetId));
			 Order order = new Order();
			 order.setEmployee(empRepo.getReferenceById(empId));
			 order.setAsset(assetRepo.getReferenceById(assetId));
	         ordRepo.save(order);
	         ticket = "Your order id is " + order.getOrderId() + " save it";
		 }else {
			 throw new NoAuthority(NOT_HAVE_AUTHORITY);
		 }
		 return ticket;
	}
	
	@Override
	public String returnRequest(long id,long orderId) throws InvalidAssetId, NoAuthority, InvalidEmployeeId, InvalidOrderId {
		 if(!empRepo.existsById(id)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
		 }
		if(!ordRepo.existsById(orderId)) {
			throw new InvalidOrderId(ORDER_NOT_EXIST);
		}
		Employee emp = empRepo.getReferenceById(id);
		Order ord = ordRepo.getReferenceById(orderId);
		if (emp.getId() ==ord.getEmployee().getId()) {	
			 emp.getAssets().remove(ord.getAsset());
			 ordRepo.updateStatusById(status.returns.value,orderId);
		}else {
			throw new NoAuthority(NOT_HAVE_AUTHORITY);
		}
		return "order "+orderId+" will be returning";
	}
	
	@Override
	public String addEmployee(long id , Employee emp) throws NoAuthority, InvalidEmployeeId, InvalidEmployeeDetails{
		 if(!empRepo.existsById(id)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
		 }
		String save = null;
		
		List<Employee> empids = empRepo.findByEmailId(emp.getEmailId());
		//empids.isEmpty() == false  -> 
		if(!empids.isEmpty()) {
			throw new InvalidEmployeeDetails("Employee Email Id is already present");
		}
		
		long level = empRepo.getReferenceById(id).getUser().getUserId();
		if (level<3) {
			   throw new NoAuthority(NOT_HAVE_AUTHORITY);
		}else{
			empRepo.save(emp);
			long ids = emp.getId();
			
			save = "Employee id = " + ids  + " added in system ";
		}
		return save;
	}
	
	@Override
	public String updateEmployee(long id,Employee emp) throws InvalidEmployeeId , NoAuthority {
		if(!empRepo.existsById(id)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		if(!empRepo.existsById(emp.getId())) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		String saveStatus = null;		
		long level = empRepo.getReferenceById(id).getUser().getUserId();
		if (level<3) {
			   throw new NoAuthority(NOT_HAVE_AUTHORITY);
		}else{
			empRepo.saveAndFlush(emp);
			saveStatus = "Employee id = " + emp.getId() + " Updated in system ";
		}
		return saveStatus;
	}
	@Override
	public String deleteEmployee(long id, long empId) throws InvalidEmployeeId, NoAuthority {
		if(!empRepo.existsById(id)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		if(!empRepo.existsById(empId)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		if(!empRepo.getReferenceById(empId).getAssets().isEmpty()) {
			throw new NoAuthority("First collect the asset from the employee");
			
		}
		List<Order>employeeOrder = ordRepo.findByEmployee(empRepo.getReferenceById(empId));
		
		String deleteStatus = null;		
		long level = empRepo.getReferenceById(id).getUser().getUserId();
		if (level<3) {
			   throw new NoAuthority(NOT_HAVE_AUTHORITY);
		}else {
			Iterable<Order>itrableList = employeeOrder;
			ordRepo.deleteAll(itrableList);
			empRepo.delete(empRepo.getReferenceById(empId));
			deleteStatus = "Employee id = " + empId + " deleted from System" ;
		}
		return deleteStatus;
		
	}
	@Override
	@Transactional
	public List<Assets> searchAssetByName(long id , String name) throws NoAssetsFound, InvalidEmployeeId{
		if(!empRepo.existsById(id)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		List<Assets>assetsByName = assetRepo.findByAssetName(name);
		if(assetsByName.isEmpty()) {
			throw new NoAssetsFound("No asset Found");
		}
		return assetsByName;
		
	}
	
	
	@Override
	public String allocateOrder(long id, long orderId) throws NoAuthority ,InvalidOrderId, InvalidEmployeeId{
		if(!empRepo.existsById(id)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
	    }
		
		boolean b = ordRepo.existsById(orderId);
		if(b) {
			if(ordRepo.getReferenceById(orderId).getStatus().equals(status.release.value)) {
				throw new NoAuthority("Order is already released");
			}
			if(!ordRepo.getReferenceById(orderId).getStatus().equals(status.pending.value)) {
				throw new NoAuthority("Order is canceled");
			}
			long level = empRepo.getReferenceById(id).getUser().getUserId();
			Order ord = ordRepo.getReferenceById(orderId);
			Employee emp2 = ord.getEmployee();
			if (level>1 && id==emp2.getManagerId()) {
				ordRepo.updateStatusById(status.allocate.value,orderId);
			}else if(level>2){
				ordRepo.updateStatusById(status.allocate.value,orderId);
			}
			else {
				throw new NoAuthority(NOT_HAVE_AUTHORITY);
			}
		}else {
			throw new InvalidOrderId(ORDER_NOT_EXIST);
		}
		return "Order id = "+orderId+" is Allocated";
	}
	
	@Override
	public String rejectOrder(long id, long orderId) throws NoAuthority ,InvalidOrderId, InvalidEmployeeId{
		if(!empRepo.existsById(id)) {
			throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		boolean b = ordRepo.existsById(orderId);
		if(b) {	
			if(ordRepo.getReferenceById(orderId).getStatus().equals(status.release.value)) {
				throw new NoAuthority("Order is already released");
			}
			if(ordRepo.getReferenceById(orderId).getStatus().equals(status.returns.value)) {
				throw new NoAuthority("Order is canceled");
			}	
			long level = empRepo.getReferenceById(id).getUser().getUserId();
			Order ord = ordRepo.getReferenceById(orderId);
			Employee emp2 = ord.getEmployee();
			if (level>1 && id==emp2.getManagerId()) {
				ordRepo.updateStatusById(status.reject.value,orderId);
			}else if(level>2){
				ordRepo.updateStatusById(status.reject.value,orderId);
			}
			else {
				throw new NoAuthority(NOT_HAVE_AUTHORITY);
			}
		}else {
			throw new InvalidOrderId("Order Id does not exist");
		}
		return "Order id = "+orderId+" is rejected";
	}
	
	@Override
	public String releaseOrder(long id, long orderId) throws NoAuthority ,InvalidOrderId, InvalidEmployeeId{
		if(!empRepo.existsById(id)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
	    }
		boolean b = ordRepo.existsById(orderId);
		if(b) {
			Order ord = ordRepo.getReferenceById(orderId);
			if(!ord.getStatus().equals(status.allocate.value)){
					throw new NoAuthority("Order is not allocated");
			}
			Employee emp2 =ord.getEmployee();
			long level = empRepo.getReferenceById(id).getUser().getUserId();
			if (level>1 && id==emp2.getManagerId()) {
					ordRepo.updateStatusById(status.release.value,orderId);
			}else if(level>2){
				ordRepo.updateStatusById(status.release.value,orderId);
			}else {
				throw new NoAuthority(NOT_YOUR_ORDER);
			}
		}else {
			throw new InvalidOrderId("Order Id does not exist");
	    }
	
		return "Order id = "+orderId+" is Released";	
    }
	
	@Override
	public String addAsset(long id ,Assets asset) throws NoAuthority, DuplicateAsset, InvalidEmployeeId {
		if(!empRepo.existsById(id)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
	    }
		String saveStatus =null;
		boolean b = assetRepo.existsById(asset.getAssetId());
		if(!b) {
			long level = empRepo.getReferenceById(id).getUser().getUserId();
			if(level>2) {
				assetRepo.save(asset);
				saveStatus = "New Asset Added, name = " + asset.getAssetName();
			}else {
				throw new NoAuthority(NOT_HAVE_AUTHORITY);
			}
	     }else {
	    	 throw new DuplicateAsset("Given Asset already exist in system");
	     }
		return saveStatus;
	}
	
	@Override
	public String updateAsset(long id ,Assets asset) throws NoAuthority, InvalidAssetId, InvalidEmployeeId {
		if(!empRepo.existsById(id)) {
			 throw new InvalidEmployeeId(INVALID_EMP_ID);
	    }
		String updateStatus = null;
		boolean b = assetRepo.existsById(asset.getAssetId());
		if(b) {
			long level = empRepo.getReferenceById(id).getUser().getUserId();
			if(level>2) {
				assetRepo.saveAndFlush(asset);
				updateStatus = "Asset Updated , name = " + asset.getAssetName();
			}else {
				throw new NoAuthority(NOT_HAVE_AUTHORITY);
			}
	     }else {
	    	 throw new InvalidAssetId("Given Asset does not exist in system");
	     }
		return updateStatus;
	}
	
	
	@Override
	public String deleteAsset(long id ,long assetId) throws NoAuthority,InvalidAssetId, InvalidEmployeeId {
		if(!empRepo.existsById(id)){
			throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		boolean b = assetRepo.existsById(assetId);
		if(!b) { 
			throw new InvalidAssetId("Given Asset does not exist in system");
		}
		long level = empRepo.getReferenceById(id).getUser().getUserId();
		if(level>2) {
			assetRepo.deleteById(assetId);
		}else {
			throw new NoAuthority(NOT_HAVE_AUTHORITY);
		}
		return "Asset Deleted";
	}
	
	@Override
	public List<Assets> showAllAssetDetails(long id)throws InvalidEmployeeId, NoAssetsFound {
		if(!empRepo.existsById(id)){
			throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		List<Assets> assetList;
		assetList = assetRepo.findAll();
		if(assetList.isEmpty()){
			throw new NoAssetsFound("System does not have asset for now");
		}else {
			return assetList;
		}
	}
	
	@Override
	public List<Ticket> showAllPendingOrder(long id) throws InvalidEmployeeId , NoAuthority, NoOrderFound {
		if(!empRepo.existsById(id)){
			throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		
		List<Ticket>tickets = new ArrayList<Ticket>();
		if(1<empRepo.getReferenceById(id).getUser().getUserId()) {
			    ordRepo.findAll().stream()
                                     .filter(order -> order.getStatus().equals(status.pending.value))
                                     .forEach(e->{
												   Ticket newTick = new Ticket();
												   newTick.setOrderId(e.getOrderId());
												   newTick.setDate(e.getOrderDate());
												   newTick.setEmployeeId(e.getEmployee().getId());
												   newTick.setAssetname(e.getAsset().getAssetName());
												   newTick.setStatus(e.getStatus());
												   tickets.add(newTick);
											   });		   
		}else {
			throw new NoAuthority(NOT_HAVE_AUTHORITY);
		}
		if(tickets.isEmpty()) {
			throw new NoOrderFound("No order pending");
		}
		return tickets;
	}
	@Override
	public List<Ticket> showAllOrder(long id) throws InvalidEmployeeId , NoAuthority, NoOrderFound {
		if(!empRepo.existsById(id)){
			throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		
		List<Ticket>tickets = new ArrayList<Ticket>();
		if(1<empRepo.getReferenceById(id).getUser().getUserId()) {
			    ordRepo.findAll().stream().forEach(e->{
												   Ticket newTick = new Ticket();
												   newTick.setOrderId(e.getOrderId());
												   newTick.setDate(e.getOrderDate());
												   newTick.setEmployeeId(e.getEmployee().getId());
												   newTick.setAssetname(e.getAsset().getAssetName());
												   newTick.setStatus(e.getStatus());
												   tickets.add(newTick);
											   });		   
		}else {
			throw new NoAuthority(NOT_HAVE_AUTHORITY);
		}
		if(tickets.isEmpty()) {
			throw new NoOrderFound("No order pending");
		}
		return tickets;
	}
	
	
	@Override
	public List<Ticket> showAllAllocatedOrder(long id) throws InvalidEmployeeId , NoAuthority, NoOrderFound {
		if(!empRepo.existsById(id)){
			throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		List<Ticket>tickets = new ArrayList<Ticket>();
		if(1<empRepo.getReferenceById(id).getUser().getUserId()) {
			ordRepo.findAll().stream()
			.filter(order -> order.getStatus().equals(status.allocate.value))
			.forEach(e->{
				Ticket newTick = new Ticket();
				newTick.setOrderId(e.getOrderId());
				newTick.setDate(e.getOrderDate());
				newTick.setEmployeeId(e.getEmployee().getId());
				newTick.setAssetname(e.getAsset().getAssetName());
				newTick.setStatus(e.getStatus());
				tickets.add(newTick);
			});		   
		}else {
			throw new NoAuthority(NOT_HAVE_AUTHORITY);
		}
		if(tickets.isEmpty()) {
			throw new NoOrderFound("No order Allocated");
		}
		return tickets;
	}
	@Override
	public List<Ticket> relesedOrder(long id) throws InvalidEmployeeId , NoAuthority, NoOrderFound {
		if(!empRepo.existsById(id)){
			throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		List<Ticket>tickets = new ArrayList<Ticket>();
		if(1<empRepo.getReferenceById(id).getUser().getUserId()) {
			ordRepo.findAll().stream()
			.filter(order -> order.getStatus().equals(status.release.value))
			.forEach(e->{
				Ticket newTick = new Ticket();
				newTick.setOrderId(e.getOrderId());
				newTick.setDate(e.getOrderDate());
				newTick.setEmployeeId(e.getEmployee().getId());
				newTick.setAssetname(e.getAsset().getAssetName());
				newTick.setStatus(e.getStatus());
				tickets.add(newTick);
			});		   
		}else {
			throw new NoAuthority(NOT_HAVE_AUTHORITY);
		}
		if(tickets.isEmpty()) {
			throw new NoOrderFound("No order Released");
		}
		return tickets;
	}
	
	@Override
	public List<EmployeeDto>showAllEmployee(long id) throws InvalidEmployeeId , NoAuthority{
		
		if(!empRepo.existsById(id)){
			throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		List<EmployeeDto>employees = new ArrayList<EmployeeDto>();
		if(1<empRepo.getReferenceById(id).getUser().getUserId()) {
		    empRepo.findAll().stream()                             
                                 .forEach(emp->{
                                	 EmployeeDto employee = new EmployeeDto();
                                	 employee.setId(emp.getId());
                                	 employee.setFirstName(emp.getFirstName());
                                	 employee.setLastName(emp.getLastName());
                                	 employee.setEmailId(emp.getEmailId());
                                	 employee.setUserId(emp.getUser().getUserId());
                                	 employee.setManagerId(emp.getManagerId());	
                                	 employees.add(employee);
									});		   
	}else {
		throw new NoAuthority(NOT_HAVE_AUTHORITY);
	}
		
		return employees;
		
	}
	@Override
	public EmployeeRedux logIn(EmployeeDto2 employee) {
		EmployeeRedux loggedEmp = new EmployeeRedux();
		if(empRepo.existsById(employee.getId()) && 
				empRepo.getReferenceById(employee.getId()).getEmailId()
				.equals(employee.getEmailId())){
					loggedEmp.setId(employee.getId());
					loggedEmp.setAuthoritylevel(empRepo.getReferenceById(employee.getId()).getUser().getUserId());
					loggedEmp.setName(empRepo.getReferenceById(employee.getId()).getFirstName());
				}
	   if(loggedEmp.getId()==0) {
		   return null;
	   }else {
		   return loggedEmp;
	   }
	}

	@Override
	public EmployeeDto getEmployeeById(long id) throws InvalidEmployeeId {
		if(!empRepo.existsById(id)){
			throw new InvalidEmployeeId(INVALID_EMP_ID);
		}
		EmployeeDto emp = new EmployeeDto();
		Employee employee = empRepo.getReferenceById(id);
		emp.setId(id);
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmailId(employee.getEmailId());
		emp.setManagerId(employee.getManagerId());
		emp.setUserId(employee.getUser().getUserId());
		
		
		return emp;
	}

}


