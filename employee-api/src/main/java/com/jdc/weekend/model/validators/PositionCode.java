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
@Constraint(validatedBy = PositionCodeConstraint.class)
public @interface PositionCode {

	String message() default "Invalid Position Code";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
}
