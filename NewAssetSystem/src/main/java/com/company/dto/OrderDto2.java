package com.company.dto;

import javax.validation.constraints.Min;

public class OrderDto2 {
	
	@Min(value = 0,message = "Put valid Order id ")
	private long orderId=1;
	
	
	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
}
