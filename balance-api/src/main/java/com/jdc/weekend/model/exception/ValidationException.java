package com.jdc.weekend.model.exception;

import java.util.Map;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Map<String, String> fieldErrorMap;
	
	public ValidationException(Map<String,  String> fieldErrorMap) {
		super(fieldErrorMap.toString());
		this.fieldErrorMap = fieldErrorMap;
	}
	
	public Map<String, String> getFieldErrorMap() {
		return fieldErrorMap;
	}
}
