package com.jdc.weekend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jdc.weekend.model.repo.EmployeeHistoryRepo;

@SpringBootTest
class BalanceApiApplicationTests {

	@Autowired
	private EmployeeHistoryRepo repo;
	
	@Test
	void contextLoads() {
		System.out.println(repo.getNextSeqNumber(1));
	}

}
