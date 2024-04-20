package com.jdc.weekend.model.security.costomize;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdc.weekend.api.output.ApiResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler{

	private final ObjectMapper objectMapper;
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if(!response.isCommitted()) {
			response.setStatus(HttpStatus.FORBIDDEN.value());
			
			var writer = response.getWriter();
			writer.append(objectMapper.writeValueAsString(ApiResponse.securityError("Unauthorize Access!")));
			writer.flush();
		}
	}

}
