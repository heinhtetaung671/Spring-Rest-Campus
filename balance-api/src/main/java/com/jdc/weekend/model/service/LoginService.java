package com.jdc.weekend.model.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import com.jdc.weekend.api.input.LoginForm;
import com.jdc.weekend.api.output.LoginResult;
import com.jdc.weekend.model.security.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final AuthenticationManager authenticationManager;
	private final JwtTokenProvider tokenProvider;
	
	@Value("${jwt.token.life-time}")
	private int tokenLifeTime;
	
	public LoginResult doLogin(LoginForm form) {
		var authentication = authenticationManager.authenticate(form.toAuthentication());
		
		return new LoginResult(LocalDateTime.now().plusMinutes(tokenLifeTime), tokenProvider.generate(authentication));
	}

}
