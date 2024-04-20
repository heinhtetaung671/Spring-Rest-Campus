package com.jdc.weekend;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jdc.weekend.api.deserializer.BalanceTypeDeserializer;
import com.jdc.weekend.api.deserializer.RoleDeserializer;
import com.jdc.weekend.api.validator.EmployeeItemValidator;
import com.jdc.weekend.model.BaseRepoImpl;
import com.jdc.weekend.model.constant.BalanceType;
import com.jdc.weekend.model.constant.Role;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseRepoImpl.class)
@EnableAsync
public class BalanceApiWebConfig implements WebMvcConfigurer {

	@Autowired
	private EmployeeItemValidator validator;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		.allowedOriginPatterns("*")
		.allowedOrigins("*")
		.allowedHeaders("*")
		.allowedMethods("*");
	}
	
	@Bean
	Jackson2ObjectMapperBuilderCustomizer objectMapper(RoleDeserializer roleDeserializer, BalanceTypeDeserializer balanceTypeDeserializer) {
		return builder -> {
			builder.deserializersByType(Map.of(Role.class, roleDeserializer, BalanceType.class, balanceTypeDeserializer));
		};
	}
	
	@Override
	public Validator getValidator() {
		return validator;
	}
}
