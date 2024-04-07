package com.jdc.weekend.exception;

import java.util.List;

public class ApiValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private List<String> messages;
	
	public ApiValidationException(List<String> messages) {
		// TODO Auto-generated constructor stub
		this.messages = messages;
	}
	
	public List<String> getMessages() {
		return messages;
	}
}
