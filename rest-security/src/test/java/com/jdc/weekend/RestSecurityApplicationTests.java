package com.jdc.weekend;

import org.junit.jupiter.api.Test;

import com.jdc.weekend.service.SecretKeys;

class RestSecurityApplicationTests {
	
	@Test
	void contextLoads() {
		var key = SecretKeys.getKeys();
		var str1 = SecretKeys.getKeys(key);
		var key2 = SecretKeys.getkey(str1);
		var str2 = SecretKeys.getKeys(key2);

		System.out.println(key);
		System.out.println(str1);
		System.out.println(str2);
		
		
	}
	

}
