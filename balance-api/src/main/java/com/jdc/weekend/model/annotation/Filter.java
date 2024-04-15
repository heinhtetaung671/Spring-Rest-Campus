package com.jdc.weekend.model.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

@Component
@Target(TYPE)
@Retention(RUNTIME)
public @interface Filter {

	@AliasFor(annotation = Component.class)
	String value() default "";
	
}
