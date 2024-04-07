package com.jdc.weekend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.jdc.weekend.model.LoginForm;
import com.jdc.weekend.model.LoginResult;

@Service
public class LoginService {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private ApiTokenProvider provider;
	
	public LoginResult login(LoginForm form) {
		
		// Login
		var authentication = authenticationManager.authenticate(form.authentication());
		// Generate Token
		var token = provider.generate(authentication);
		
		return new LoginResult("ApiUser", token);
		
	}

}
