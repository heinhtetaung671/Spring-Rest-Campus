package com.jdc.weekend.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import jakarta.validation.constraints.NotBlank;

public record LoginForm(
		@NotBlank(message = "Please enter login id.")
		String username,
		@NotBlank(message = "Please enter password.")
		String password
		) {

	public Authentication authentication() {
		// TODO Auto-generated method stub
		return UsernamePasswordAuthenticationToken.unauthenticated(username, password);
	}

}
