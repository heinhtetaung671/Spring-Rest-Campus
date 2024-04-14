package com.jdc.weekend.api.input;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

public record LoginForm(String loginId, String password) {

	public Authentication toAuthentication() {
		return UsernamePasswordAuthenticationToken.unauthenticated(loginId, password);
	}
	
}
