package com.jdc.weekend.model.eventHandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.constant.Status;
import com.jdc.weekend.model.entity.Account;
import com.jdc.weekend.model.event.employee.AccountCreateAndSetEvent;
import com.jdc.weekend.model.repo.AccountRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountCreateAndSetEventHandler {

	@Value("${employee.default-password}")
	private String employeeDefaultPassword;
	private final AccountRepo accountRepo;
	private final PasswordEncoder passwordEncoder;
	
	@EventListener
	@Transactional
	void handle(final AccountCreateAndSetEvent event) {
		var account = new Account();
		account.setLoginId(event.getEmployee().getEmail());
		account.setPassword(passwordEncoder.encode(employeeDefaultPassword));
		account.setStatus(Status.Applied);
		account.setName(event.getAccountName());
		accountRepo.save(account);
		event.getEmployee().setAccount(account);
	}
	
}
