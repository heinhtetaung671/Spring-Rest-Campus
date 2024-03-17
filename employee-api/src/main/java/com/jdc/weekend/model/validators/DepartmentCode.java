package com.jdc.weekend.model.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, PARAMETER })
@Constraint(validatedBy = DepartmentCodeConstraint.class)
public @interface DepartmentCode {

	String message() default "Invalid Department Code";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
