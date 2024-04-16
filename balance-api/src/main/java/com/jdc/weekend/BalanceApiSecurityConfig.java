package com.jdc.weekend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.jdc.weekend.model.security.filter.JwtTokenExceptionHandlingFilter;
import com.jdc.weekend.model.security.filter.JwtTokenFilter;

@Configuration
@PropertySource(value = "classpath:/balance-api.properties")
public class BalanceApiSecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter,
			JwtTokenExceptionHandlingFilter jwtTokenExceptionHandlingFilter) throws Exception {
		http.csrf(csrf -> csrf.disable());
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.authorizeHttpRequests(req -> {
			req.requestMatchers(new AntPathRequestMatcher("/login", "POST")).permitAll();
			req.requestMatchers("/employee/**").permitAll();
			req.requestMatchers( "/ledger/**", "/category/**").hasAnyAuthority("Admin");
			req.requestMatchers("/report").hasAnyAuthority("Admin");
			req.anyRequest().authenticated();
		});

		http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
		http.addFilterBefore(jwtTokenExceptionHandlingFilter, JwtTokenFilter.class);
		return http.build();
	}
	
	@Bean
	AuthenticationProvider provider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		var provider = new DaoAuthenticationProvider(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		provider.setHideUserNotFoundExceptions(false);
		return provider;
	}

	@Bean
	RequestMatcher loginRequestMatcher() {
		return new AntPathRequestMatcher("/login", "POST", true);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration conf) throws Exception {
		if (conf.getAuthenticationManager() instanceof ProviderManager pm) {
			pm.setEraseCredentialsAfterAuthentication(false);
			return pm;
		}
		throw new RuntimeException();
	}

}
