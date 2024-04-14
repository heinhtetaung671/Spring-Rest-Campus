package com.jdc.weekend.model.repo;

import java.util.Optional;

import com.jdc.weekend.model.BaseRepo;
import com.jdc.weekend.model.entity.Account;

public interface AccountRepo extends BaseRepo<Account, Integer>{

	Optional<Account> findAccountByLoginId(String email);
	
}
