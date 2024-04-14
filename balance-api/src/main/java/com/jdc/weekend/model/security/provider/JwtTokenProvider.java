package com.jdc.weekend.model.security.provider;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.jdc.weekend.model.exception.InvalidTokenException;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Arrays;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtTokenProvider {

	@Value("${jwt.secret-key}")
	private String strKey;
	@Value("${jwt.token.issuer}")
	private String issuer;
	@Value("${jwt.token.life-time}")
	private int tokenLifeTime;

	private SecretKey key;

	private static final String CLAIM_KEY_FOR_AUTHORITIES = "authorities";
	private static final String CLAIM_KEY_FOR_PASSWORD = "password";

	private final AuthenticationManager authenticationManager;

	@PostConstruct
	public void init() {
		try {
			key = JwtKeyProvider.getKey(strKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String generate(Authentication authentication) {
		var authorities = authentication.getAuthorities().stream().map(a -> a.getAuthority())
				.collect(Collectors.joining(","));

		var today = new Date();
		var calender = Calendar.getInstance();
		calender.setTime(today);
		calender.add(Calendar.MINUTE, tokenLifeTime);

		return Jwts.builder().issuer(issuer).signWith(key).issuedAt(today).expiration(calender.getTime())
				.subject(authentication.getName()).claims(Map.of(CLAIM_KEY_FOR_AUTHORITIES, authorities,
						CLAIM_KEY_FOR_PASSWORD, authentication.getCredentials().toString()))
				.compact();
	}

	public Authentication parse(String token) {
		var jwt = Jwts.parser().requireIssuer(issuer).verifyWith(key).build().parseSignedClaims(token);
		var principle = jwt.getPayload().getSubject();	
		var credential = jwt.getPayload().get(CLAIM_KEY_FOR_PASSWORD, String.class);
		var authorities = Arrays.asList(jwt.getPayload().get(CLAIM_KEY_FOR_AUTHORITIES, String.class).split(","))
				.stream().map(SimpleGrantedAuthority::new).toList();

		try {
			var authentication = authenticationManager
					.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(principle, credential));
			((UsernamePasswordAuthenticationToken) authentication).eraseCredentials();
			return authentication;
		} catch (JwtException | AuthenticationException e) {
			throw new InvalidTokenException();
		}
		
		
		
	}

}
