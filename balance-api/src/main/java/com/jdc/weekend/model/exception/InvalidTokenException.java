package com.jdc.weekend.model.exception;

public class InvalidTokenException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidTokenException() {
		super("Authorization is invalid or expired!.");
	}
	
	public InvalidTokenException(String message) {
		super(message);
	}
}
