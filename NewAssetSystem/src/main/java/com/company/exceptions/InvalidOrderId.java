package com.company.exceptions;

public class InvalidOrderId extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidOrderId(String str) {
		super(str);
	}
}
