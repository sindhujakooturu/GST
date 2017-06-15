package com.gst.organisation.sacdata.exception;

public class SacdataException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private final String msg;
	
	public SacdataException(String errorMessage) {
		this.msg = errorMessage;
	}

	public String getMsg() {
		return msg;
	}
	
	
}
	

