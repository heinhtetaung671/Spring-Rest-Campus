package com.jdc.weekend.api.exceptionHandler;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jdc.weekend.api.output.ApiResponse;
import com.jdc.weekend.model.exception.ValidationException;

@RestControllerAdvice
public class ApiMethodExceptionHandler {

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	ApiResponse<Map<String, List<String>>> handleValidationException(ValidationException e){
		return ApiResponse.validationError(e.getFieldErrorMap());
	}
	
}
