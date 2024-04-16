package com.jdc.weekend.api.output;

import java.time.LocalDateTime;

import com.jdc.weekend.model.constant.ApiResponseStatus;

public record ApiResponse<T>(ApiResponseStatus status, LocalDateTime responseTime, T payload) {

	public static <T> ApiResponse<T> validationError(T payload){
		return new ApiResponse<T>(ApiResponseStatus.VALIDATION_ERROR, LocalDateTime.now(), payload);
	}
	
	public static <T> ApiResponse<T> securityError(T payload){
		return new ApiResponse<T>(ApiResponseStatus.SECURITY_ERROR, LocalDateTime.now(), payload);
	}
	
	public static <T> ApiResponse<T> success(T payload){
		return new ApiResponse<T>(ApiResponseStatus.SUCCESS, LocalDateTime.now(), payload);
	}
	
}
