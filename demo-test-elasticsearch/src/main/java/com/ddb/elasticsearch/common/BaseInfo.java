package com.ddb.elasticsearch.common;

public class BaseInfo {
	public static final String SUCCESS_MESSAGE = "success";
	public static final String ERROR_MESSAGE = "success";
	public static final int SUCCESS_CODE = 200;
	public static final int ERROR_CODE = 500;
	
	private String message = SUCCESS_MESSAGE;
	private int status = SUCCESS_CODE;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	

}
