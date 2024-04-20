package com.jdc.weekend.model.exception;

import java.util.List;
import java.util.Map;

public class ValidationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private Map<String, List<String>> fieldErrorMap;
	
	public ValidationException(Map<String,  List<String>> fieldErrorMap) {
		super(fieldErrorMap.toString());
		this.fieldErrorMap = fieldErrorMap;
	}
	
	public Map<String, List<String>> getFieldErrorMap() {
		return fieldErrorMap;
	}
}
