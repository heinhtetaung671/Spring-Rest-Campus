package com.jdc.weekend.api;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.weekend.api.input.LoginForm;
import com.jdc.weekend.api.output.LoginResult;
import com.jdc.weekend.model.service.LoginService;

import lombok.RequiredArgsConstructor;

@RequestMapping("login")
@RestController
@RequiredArgsConstructor
public class LoginApi {

	private final LoginService service;
	
	@PostMapping
	LoginResult login(@RequestBody @Validated LoginForm form, BindingResult result) {
		return service.doLogin(form);
	}
	
}
