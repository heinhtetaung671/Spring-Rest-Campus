package com.jdc.weekend.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.weekend.exception.ApiValidationException;
import com.jdc.weekend.model.LoginForm;
import com.jdc.weekend.model.LoginResult;
import com.jdc.weekend.service.LoginService;

@RestController
@RequestMapping("public")
public class LoginApi {

	@Autowired
	private LoginService service;
	
	@PostMapping("login")
	LoginResult login(@Validated @RequestBody LoginForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			throw new ApiValidationException(result.getFieldErrors().stream().map(e -> e.getDefaultMessage()).toList());
		}
		
		service.login(form);
		
		return null;
	}
	
}
