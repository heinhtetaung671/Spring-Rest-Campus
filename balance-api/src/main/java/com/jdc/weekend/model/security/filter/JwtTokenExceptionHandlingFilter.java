package com.jdc.weekend.model.security.filter;

import java.io.IOException;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdc.weekend.api.output.ApiResponse;
import com.jdc.weekend.model.annotation.Filter;
import com.jdc.weekend.model.exception.InvalidTokenException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Filter
public class JwtTokenExceptionHandlingFilter extends OncePerRequestFilter {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (InvalidTokenException e) {
			if (!response.isCommitted()) {
				response.setStatus(Response.SC_FORBIDDEN);
				
				var writer = response.getWriter();
				writer.write(objectMapper.writeValueAsString(ApiResponse.securityError(e.getMessage())));
				writer.flush();
			}
		}
	}

}
