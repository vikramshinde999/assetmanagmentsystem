package com.company.exceptions;


import java.time.LocalDateTime;

public class ExceptionResponce {
	public static final String INVALID_ARGUMENTS = null;
	private String errorMsg;
	private String errorCode;
	private LocalDateTime timeStamp;
	public ExceptionResponce(String errorMsg, String errorCode, LocalDateTime timeStamp) {
		super();
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
		this.timeStamp = timeStamp;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
}