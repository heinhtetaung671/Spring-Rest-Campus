package com.jdc.weekend.model.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jdc.weekend.model.annotation.Filter;
import com.jdc.weekend.model.security.provider.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Filter
public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		var token = request.getHeader("Authorization");
		var emptyContext = SecurityContextHolder.createEmptyContext();
		emptyContext.setAuthentication(jwtTokenProvider.parse(token));
		SecurityContextHolder.setContext(emptyContext);
		
		filterChain.doFilter(request, response);
	}

}
