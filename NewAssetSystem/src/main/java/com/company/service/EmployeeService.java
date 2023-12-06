package com.company.service;

import java.util.List;

import com.company.dto.EmployeeDto;
import com.company.dto.EmployeeDto2;
import com.company.dto.EmployeeRedux;
import com.company.entity.Assets;
import com.company.entity.Employee;
import com.company.entity.Ticket;
import com.company.exceptions.DuplicateAsset;
import com.company.exceptions.InvalidAssetId;
import com.company.exceptions.InvalidEmployeeDetails;
import com.company.exceptions.InvalidEmployeeId;
import com.company.exceptions.InvalidOrderId;
import com.company.exceptions.NoAssetsFound;
import com.company.exceptions.NoAuthority;
import com.company.exceptions.NoOrderFound;

public interface EmployeeService {
	
	public String  raiseRequest(long id,long empId,long assetId) throws InvalidAssetId,InvalidEmployeeId ,NoAuthority, DuplicateAsset;
	public String returnRequest(long id,long assetId)throws InvalidAssetId, NoAuthority, InvalidEmployeeId, InvalidOrderId;
	public String addEmployee(long id , Employee emp) throws NoAuthority, InvalidEmployeeId, InvalidEmployeeDetails;
	public String allocateOrder(long id,long orderId) throws NoAuthority, InvalidOrderId, InvalidEmployeeId ;
	public String addAsset(long id,Assets asset) throws NoAuthority, DuplicateAsset, InvalidEmployeeId;
	public String releaseOrder(long id, long orderId) throws NoAuthority, InvalidOrderId, InvalidEmployeeId;
	public String updateAsset(long id, Assets asset) throws NoAuthority, InvalidAssetId, InvalidEmployeeId;
	public String deleteAsset(long id, long assetId) throws NoAuthority, InvalidAssetId, InvalidEmployeeId;
	public List<Assets> showAllAssetDetails(long Id) throws InvalidEmployeeId, NoAssetsFound;
	public List<Ticket> showAllPendingOrder(long id) throws InvalidEmployeeId, NoAuthority, NoOrderFound;
	public String updateEmployee(long id, Employee emp) throws InvalidEmployeeId, NoAuthority;
	public String deleteEmployee(long id, long empId) throws InvalidEmployeeId, NoAuthority;
	public String rejectOrder(long id, long orderId) throws NoAuthority, InvalidOrderId, InvalidEmployeeId;
	public List<Ticket> showAllAllocatedOrder(long id) throws InvalidEmployeeId, NoAuthority, NoOrderFound;
	List<Assets> searchAssetByName(long id, String name) throws NoAssetsFound, InvalidEmployeeId;
	List<Ticket> showAllOrder(long id) throws InvalidEmployeeId, NoAuthority, NoOrderFound;
	List<EmployeeDto> showAllEmployee(long id) throws InvalidEmployeeId, NoAuthority;
	EmployeeRedux logIn(EmployeeDto2 employee);
	List<Ticket> relesedOrder(long id) throws InvalidEmployeeId, NoAuthority, NoOrderFound;
	public EmployeeDto getEmployeeById(long id) throws InvalidEmployeeId;
	
}
