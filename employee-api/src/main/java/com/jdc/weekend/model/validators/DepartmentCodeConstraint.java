package com.jdc.weekend.model.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.repo.DepartmentRepo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Service
public class DepartmentCodeConstraint implements ConstraintValidator<DepartmentCode, String>{

	@Autowired
	private DepartmentRepo repo;
	
	@Override
	@Transactional(readOnly = true)
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return repo.countBycode(value) == 0L;
	}

}
