package com.company.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmployeeDto {
	
	
	private long id ;
	
	@Size(min = 3, max = 50, message = "Invalid Employee Name please enter a vaild Employee first Name!")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the first name")
	private String firstName;
	
	@Size(min = 3, max = 50, message = "Invalid Employee Name please enter a vaild Employee last Name!")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Accepts only alphabets! re-enter the last name")
	private String LastName;
	
	@Email(message = "Email should be valid")
	private String emailId;
	
	@Min(value=0,message = "put valid id in manager id")
	private long managerId;
	
	@Min(value=1,message = "put valid id in Role id")
	@Max(value=3,message = "put valid id in Role id" )
	private int userId;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public long getManagerId() {
		return managerId;
	}
	public void setManagerId(long managerId) {
		this.managerId = managerId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
