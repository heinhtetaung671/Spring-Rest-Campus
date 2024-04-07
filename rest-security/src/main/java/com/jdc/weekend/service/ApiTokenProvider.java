package com.jdc.weekend.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class ApiTokenProvider {

	@Value("${api.token.issusr}")
	private String issuer;
	@Value("${api.token.lifetime}")
	private int lifetime;
	@Value("${api.token.secret}")
	private String secret;
	
	private SecretKey key;
	
	public void init() {
		key = SecretKeys.getkey(secret);
	}
	
	private static final String ROLE = "rol";
	
	public String generate(Authentication authentication) {
		
		String roles = authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(","));
		var issueAt = new Date();
		var calendar = Calendar.getInstance();
		calendar.setTime(issueAt);
		calendar.add(Calendar.MINUTE, lifetime);
		
		return Jwts.builder().subject(authentication.getName())
			.issuer(issuer)
			.claim(ROLE, roles)
			.issuedAt(issueAt)
			.expiration(calendar.getTime())
			.signWith(key).compact();
	}

	public Authentication parse(String token) {
		if(org.springframework.util.StringUtils.hasLength(token)) {
			var jwt = Jwts.parser().requireIssuer(token).decryptWith(key).build().parseSignedClaims(token);
			var username = jwt.getPayload().getSubject();
			var roles = jwt.getPayload().get(ROLE, String.class);
			var authorities = Arrays.stream(roles.split(",")).map(a -> new SimpleGrantedAuthority(a)).toList();
			
			return UsernamePasswordAuthenticationToken.authenticated(username, null, authorities);
		}
		
		return null;
	}

	
	
}
