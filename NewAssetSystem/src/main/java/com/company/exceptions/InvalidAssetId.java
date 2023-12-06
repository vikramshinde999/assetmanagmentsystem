package com.company.exceptions;

public class InvalidAssetId extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidAssetId(String str) {
		super(str);
	}

}
