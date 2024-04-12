package com.jdc.weekend.model.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.api.input.EmployeeForm;
import com.jdc.weekend.api.input.EmployeeSearch;
import com.jdc.weekend.api.input.EmployeeStatusForm;
import com.jdc.weekend.api.output.EmployeeInfo;
import com.jdc.weekend.api.output.EmployeeInfoDetails;
import com.jdc.weekend.model.event.employee.AccountCreateAndSetEvent;
import com.jdc.weekend.model.repo.EmployeeRepo;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepo employeeRepo;
	private final ApplicationEventPublisher publisher;
	
	private static final String DOMAIN = "Employee";
	
	public EmployeeForm findByIdForEdit(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public EmployeeInfo update(int id, EmployeeForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public EmployeeInfo updateStatus(int id, EmployeeStatusForm form) {

		return null;
	}

	@Transactional
	public EmployeeInfo create(EmployeeForm form) {
		var employee = form.entity();
		publisher.publishEvent(new AccountCreateAndSetEvent(employee, form.name()));
		return EmployeeInfo.from(employeeRepo.save(employee));
	}

	public EmployeeInfoDetails findById(int id) {
		return null;
	}

	public Page<EmployeeInfo> search(EmployeeSearch search, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
