package com.company.dto;

import javax.validation.constraints.Min;

public class OrderDto {
	

	@Min(value = 0,message = "Put valid employee id ")
	private long empId=1 ;
	@Min(value = 0,message = "Put valid asset id ")
	private long assetId=1;
	
	
	public long getEmpId() {
		return empId;
	}
	public void setEmpId(long empId) {
		this.empId = empId;
	}
	public long getAssetId() {
		return assetId;
	}
	public void setAssetId(long assetId) {
		this.assetId = assetId;
	}
}
