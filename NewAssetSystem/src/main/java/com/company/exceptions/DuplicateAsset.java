package com.company.exceptions;

public class DuplicateAsset  extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DuplicateAsset(String str) {
		super(str);
	}

}
