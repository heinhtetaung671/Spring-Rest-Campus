package com.jdc.weekend.exception;

import java.util.List;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiValidationExceptionHandler {

	List<String> handle(ApiValidationException e){
		return e.getMessages();
	}
	
}
