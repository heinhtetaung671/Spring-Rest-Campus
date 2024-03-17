package com.jdc.weekend.model.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jdc.weekend.model.repo.PositionRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Service
public class PositionCodeConstraint implements ConstraintValidator<PositionCode, String>{

	@Autowired
	private PositionRepo repo;
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return repo.countByCode(value) == 0L;
	}
	
}

