package com.jdc.weekend;

import org.junit.jupiter.api.Test;

import com.jdc.weekend.model.security.provider.JwtKeyProvider;

class BalanceApiApplicationTests {

	@Test
	void contextLoads() {
		var key = JwtKeyProvider.getKey();
		
		var strKey = JwtKeyProvider.getKey(key);
		var realKey = JwtKeyProvider.getKey(strKey);
		
		var strKey2 = JwtKeyProvider.getKey(realKey);
		
		System.out.println(strKey);
		System.out.println(strKey2);
	}

}
