package com.jdc.weekend.model.exception;

public class ApiBusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ApiBusinessException(String message) {
		super(message);
	}
	
}
