package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.entity.Employee;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountCreateAndSetEvent {

	private final Employee employee;
	private final String accountName;
}
