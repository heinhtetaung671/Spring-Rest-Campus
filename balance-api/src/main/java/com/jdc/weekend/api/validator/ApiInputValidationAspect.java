package com.jdc.weekend.api.validator;

import java.util.stream.Collectors;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.jdc.weekend.model.exception.ValidationException;

@Aspect
@Component
public class ApiInputValidationAspect {

	@Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
	public void apiMethods() {
	}

	@Before(value = "apiMethods() && args(..,result)", argNames = "result")
	public void validateApiMethod(BindingResult result) {
		if (result.hasErrors()) {
			throw new ValidationException(result.getFieldErrors().stream()
					.collect(Collectors.toMap(f -> f.getField(), f -> f.getDefaultMessage())));
		}
	}

}
