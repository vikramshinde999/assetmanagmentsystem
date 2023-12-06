package com.company.exceptions;

public class InvalidEmployeeDetails extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidEmployeeDetails(String msg) {
		super(msg);
	}

}
