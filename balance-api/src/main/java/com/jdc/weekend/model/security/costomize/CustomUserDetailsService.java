package com.jdc.weekend.model.security.costomize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jdc.weekend.model.constant.Status;
import com.jdc.weekend.model.repo.AccountRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private AccountRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		var account = repo.findAccountByLoginId(username).orElseThrow(() -> new UsernameNotFoundException(username));
		return User.withUsername(username)
				.password(account.getPassword())
				.authorities(account.getEmployee().getRole().name())
				.credentialsExpired(account.getStatus() == Status.Applied ? true : false)
				.accountExpired(account.getStatus() == Status.Resigned ? true : false)
				.build();
	}

}
