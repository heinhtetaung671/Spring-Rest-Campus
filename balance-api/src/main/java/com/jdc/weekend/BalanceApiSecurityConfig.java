package com.jdc.weekend;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:/balance-api.properties")
public class BalanceApiSecurityConfig {

//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests(req -> {
//			req.requestMatchers("/employee").permitAll();
//		});
//		
//		return http.build();
//	}
	
}
