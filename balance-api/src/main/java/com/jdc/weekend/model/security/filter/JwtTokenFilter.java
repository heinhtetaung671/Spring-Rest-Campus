package com.jdc.weekend.model.security.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestMatcher;
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
	private RequestMatcher loginRequestMatcher;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!loginRequestMatcher.matches(request)) {
			var token = request.getHeader("Authorization").trim();
			var emptyContext = SecurityContextHolder.createEmptyContext();
			emptyContext.setAuthentication(jwtTokenProvider.parse(token));
			SecurityContextHolder.setContext(emptyContext);
		}

		filterChain.doFilter(request, response);
	}

}
