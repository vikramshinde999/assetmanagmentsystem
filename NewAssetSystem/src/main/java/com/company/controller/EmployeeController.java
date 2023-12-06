package com.company.controller;

import java.util.List;

import javax.validation.Valid;

import com.company.dto.AssetDto;

import com.company.dto.EmployeeDto;
import com.company.dto.EmployeeDto2;
import com.company.dto.EmployeeRedux;
import com.company.dto.OrderDto;
import com.company.dto.OrderDto2;
import com.company.entity.Assets;
import com.company.entity.Employee;
import com.company.entity.Ticket;
import com.company.entity.Users;
import com.company.exceptions.DuplicateAsset;
import com.company.exceptions.InvalidAssetId;
import com.company.exceptions.InvalidEmployeeDetails;
import com.company.exceptions.InvalidEmployeeId;
import com.company.exceptions.InvalidOrderId;
import com.company.exceptions.NoAssetsFound;
import com.company.exceptions.NoAuthority;
import com.company.exceptions.NoOrderFound;
import com.company.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/company" )
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	//place order
	@PostMapping(path="/{id}/order/raise")
	public String addOrder(@PathVariable("id") long id,@Valid @RequestBody OrderDto order ) throws InvalidAssetId, InvalidEmployeeId, NoAuthority, DuplicateAsset  {
		
		long empId = order.getEmpId();
		long assetId = order.getAssetId();
		return empService.raiseRequest(id,empId ,assetId);
		
	}
	
	//return order
	@PutMapping(path = "/{id}/order/return",consumes = "application/json")
	public String returnOrder(@PathVariable("id") long id,@Valid @RequestBody OrderDto2 order) throws InvalidAssetId, NoAuthority, InvalidEmployeeId, InvalidOrderId {
		
		long orderId = order.getOrderId();
		return empService.returnRequest(id,orderId);
	}
	
	@GetMapping(path ="/{id}/order/allorders")
	public List<Ticket> shoAllRequest(@PathVariable("id")long id) throws InvalidEmployeeId, NoAuthority, NoOrderFound {
		return empService.showAllOrder(id);
	}
	
	//allocate the order
	@PutMapping(path="/{id}/order/allocate/{ordid}")
	public String allocateOrder(@PathVariable("id") long id ,@PathVariable("ordid") long ordid) throws NoAuthority, InvalidOrderId, InvalidEmployeeId{
		long orderId = ordid;
		return empService.allocateOrder(id,orderId);
		
	}
	
	
	//get pending order ticket
	@GetMapping(path = "/{id}/order/pending")
	public List<Ticket> showPendingRequest(@PathVariable("id")long id) throws InvalidEmployeeId, NoAuthority, NoOrderFound {
		System.out.println( empService.showAllPendingOrder(id));
		return empService.showAllPendingOrder(id);
	}
	
	//reject the order
	@PutMapping(path="/{id}/order/reject/{ordid}")
	public String rejectsAssetOrder(@PathVariable("id") long id ,@PathVariable("ordid") long ordid) throws NoAuthority, InvalidOrderId, InvalidEmployeeId{
		long orderId = ordid;
		return empService.rejectOrder(id,orderId);
		
	}
	
	//get allocate order ticket
	@GetMapping(path = "/{id}/order/allocated")
	public List<Ticket> showAllocateRequest(@PathVariable("id")long id) throws InvalidEmployeeId, NoAuthority ,NoOrderFound{
		return empService.showAllAllocatedOrder(id);
	}
	
		//Release order
	@PutMapping(path="/{id}/order/release/{ordid}")
	public String rereleaseOrders(@PathVariable long id ,@PathVariable("ordid") long ordid) throws NoAuthority ,InvalidOrderId, InvalidEmployeeId{
		long orderId =  ordid;
		return empService.releaseOrder(id,orderId);
	}
	
	//search asset
	@GetMapping(path="/{id}/assets/search",consumes = "application/json")
	public List<Assets> searchAsset(@PathVariable long id ,@Valid @RequestBody AssetDto asset) throws  InvalidEmployeeId, NoAssetsFound{
		return empService.searchAssetByName(id,asset.getAssetName());
	}
	
	//Employee Add
	@PostMapping(path ="/{id}/emplyeee/addemp")
	public String createEmployee(@PathVariable("id") long id ,@Valid @RequestBody EmployeeDto employee) throws NoAuthority, InvalidEmployeeId, InvalidEmployeeDetails {
		Users user = new Users();
		user.setUserId(employee.getUserId());
		Employee emp = new Employee();
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setManagerId(employee.getManagerId());
		emp.setEmailId(employee.getEmailId());
		emp.setUser(user);
		return empService.addEmployee(id, emp);
	}
	
	//Employee update
	@PutMapping(path ="/{id}/employee/updateemp",consumes = "application/json")
	public String updateCompanyEmployee(@PathVariable("id") long id ,@Valid @RequestBody EmployeeDto employee) throws NoAuthority, InvalidEmployeeId{
		Users user = new Users();
		user.setUserId(employee.getUserId());
		Employee emp = new Employee();
		emp.setId(employee.getId());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setManagerId(employee.getManagerId());
		emp.setEmailId(employee.getEmailId());
		emp.setUser(user);
		return empService.updateEmployee(id, emp);
	}
	
	//Delete Employee
//	@DeleteMapping(path ="/{id}/employee/deleteemp",consumes = "application/json")
//	public String deleteCompanyEmployee(@PathVariable("id") long id ,@Valid @RequestBody EmployeeDto2 employee) throws NoAuthority, InvalidEmployeeId{
//		long empId = employee.getId();
//		return empService.deleteEmployee(id, empId);
//	}
	@DeleteMapping(path ="/{id}/employee/deleteemp/{empId}",consumes = "application/json")
	public String deleteCompanyEmployee(@PathVariable("id") long id,@PathVariable("empId") long empId ) throws NoAuthority, InvalidEmployeeId{		
		return empService.deleteEmployee(id, empId);
	}
	
	//show all assets
	@GetMapping(path = "/{id}/asset/allasset")
	public List<Assets> showAllAsset(@PathVariable("id") long id) throws InvalidEmployeeId, NoAssetsFound {
		return empService.showAllAssetDetails(id);
	}
	
	//add asset
	@PostMapping(path = "/{id}/asset/add",consumes = "application/json")
	public String addNewAsset(@PathVariable("id") long id,@Valid @RequestBody AssetDto assetDto) throws NoAuthority, DuplicateAsset, InvalidEmployeeId {
		Assets asset = new Assets();
		asset.setAssetName(assetDto.getAssetName());
		return empService.addAsset(id, asset);
	}
	
	
	//update the asset
	@PutMapping(path = "/{id}/asset/update",consumes = "application/json")
	public String updateNewAsset(@PathVariable("id") long id,@Valid @RequestBody Assets asset) throws NoAuthority, InvalidAssetId, InvalidEmployeeId {
		return empService.updateAsset(id, asset);
	}
	
//	//delete
//	@DeleteMapping(path = "/{id}/asset/delete",consumes = "application/json")
//	public String deleteAsset(@PathVariable("id") long id,@Valid @RequestBody AssetDto2 asset) throws NoAuthority, InvalidAssetId, InvalidEmployeeId {
//		long assetId = asset.getAssetId();
//		return empService.deleteAsset(id,assetId);
//	}
	@DeleteMapping(path = "/{id}/asset/delete/{asid}")
	public String deleteAsset(@PathVariable("id") long id,@PathVariable("asid") long assetId ) throws NoAuthority, InvalidAssetId, InvalidEmployeeId {
		return empService.deleteAsset(id,assetId);
	}
	
   @GetMapping(path="/{id}/employee/allemp")
   public List<EmployeeDto> getAllEmployee(@PathVariable("id") long id) throws InvalidEmployeeId, NoAuthority{
	   
	   return empService.showAllEmployee(id);
	   
   }
   @GetMapping(path="/login",consumes = "application/json")
   public EmployeeRedux logIn(@RequestBody EmployeeDto2 employee) { 
	return empService.logIn(employee);
   }
   
   @GetMapping(path="/{id}/order/released")
   public List<Ticket> showReleasedOrder(@PathVariable("id")long id) throws InvalidEmployeeId, NoAuthority ,NoOrderFound{
		return empService.relesedOrder(id);
	}
   
   @GetMapping(path="/employee/{id}")
   public EmployeeDto getEmployee(@PathVariable("id")long id) throws InvalidEmployeeId{
		return empService.getEmployeeById(id);
	}
}
