package com.jdc.weekend.model.security.provider;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.Jwts;

public class JwtKeyProvider {

	public static SecretKey getKey() {
		return Jwts.SIG.HS512.key().build();
	}
	
	public static SecretKey getKey(String key) {
		var bytes = Base64.getDecoder().decode(key);
		return new SecretKeySpec(bytes, "HmacSHA512");
	}
	
	public static String getKey(SecretKey key) {
		var bytes = key.getEncoded();
		return Base64.getEncoder().encodeToString(bytes);
	}
	
}
