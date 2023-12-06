package com.company.entity;

import java.sql.Date;
import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name ="orders")
public class Order {
	
	@Id
	@SequenceGenerator(
			name = "order_sequence",
			sequenceName = "order_sequence",
			allocationSize = 1)
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "order_sequence")
	@Min(value = 1,message = "Put valid Order id ")
	private long orderId;
	
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emp_id", nullable=false)
    
	private Employee employee;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="asset_id", nullable=false)
	private Assets asset;
	
	private String status = "Pending";//only three values will be here 1. Pending 2.Allocated 3.Returning 4 release
	
	@Column(name = "order_date")
	private Date OrderDate =Date.valueOf(LocalDate.now());

	public long getOrderId() {
		return orderId;
	}
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Assets getAsset() {
		return asset;
	}

	public void setAsset(Assets asset) {
		this.asset = asset;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
 
}
