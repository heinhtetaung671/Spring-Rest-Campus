package com.jdc.weekend.model;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.weekend.api.output.ApiResponse;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiResponse<List<String>> handle(ValidationException e){
		return ApiResponse.fails(e.getMessages());
	}
	
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ApiResponse<String> handle(BusinessException e){
		return ApiResponse.fails(e.getMessage());
	}
}
