package com.jdc.weekend;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class EmployeeApiMvcConfig implements WebMvcConfigurer {

	@Value("${spring.mvc.format.date}")
	private String datePattern;
	@Value("${spring.mvc.format.date-time}")
	private String dateTimePattern;

	@Bean
	Jackson2ObjectMapperBuilderCustomizer builderCustomizer() {
		return builder -> {
			builder.serializers(new LocalDateSerializer(DateTimeFormatter.ofPattern(datePattern)),
					new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimePattern)));
			builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(datePattern)),
					new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(dateTimePattern)));
		};
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedHeaders("*")
		.allowedMethods("*")
		.allowedOrigins("*");
	}

}
