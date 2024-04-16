package com.jdc.weekend.model.security.provider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
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

	@PostConstruct
	public void init() {
		try {
			key = JwtKeyProvider.getKey(strKey);
		} catch (Exception e) {
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
				.subject(authentication.getName()).claims(Map.of(CLAIM_KEY_FOR_AUTHORITIES, authorities)).compact();
	}

	public Authentication parse(String token) {
		if (StringUtils.hasLength(token)) {
			try {
				var jwt = Jwts.parser().requireIssuer(issuer).verifyWith(key).build().parseSignedClaims(token.trim());
				var principle = jwt.getPayload().getSubject();
				var strAuthorities = jwt.getPayload().get(CLAIM_KEY_FOR_AUTHORITIES, String.class);
				List<? extends GrantedAuthority> authorityList = strAuthorities == null ? new ArrayList<GrantedAuthority>()
						: Arrays.asList(strAuthorities.split(",")).stream().map(SimpleGrantedAuthority::new).toList();

				return UsernamePasswordAuthenticationToken.authenticated(principle, null, authorityList);
			} catch (JwtException e) {
				return getAnonymousAuthenticationToken();
			}
		}
		return getAnonymousAuthenticationToken();
	}

	private Authentication getAnonymousAuthenticationToken() {
		return new AnonymousAuthenticationToken("anonymousUser", "anonymousUser",
				List.of(new SimpleGrantedAuthority("Anonymous")));

	}

}
