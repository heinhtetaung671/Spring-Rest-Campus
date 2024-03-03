package com.jdc.weekend.model;

import java.util.List;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private List<String> messages;
	
	public List<String> getMessages() {
		return messages;
	}
	
	public ValidationException(List<String> messages) {
		this.messages = messages;
	}

}
