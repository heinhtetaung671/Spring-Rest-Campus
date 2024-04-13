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
import com.jdc.weekend.model.Common;
import com.jdc.weekend.model.event.employee.AccountCreateAndSetEvent;
import com.jdc.weekend.model.event.employee.EmployeeCreationEvent;
import com.jdc.weekend.model.event.employee.EmployeeStatusChangeEvent;
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
		return EmployeeForm.from(Common.getOne(employeeRepo.findById(id), DOMAIN, "Id"));
	}
 
	@Transactional
	public EmployeeInfo update(int id, EmployeeForm form) {
		var employee = Common.getOne(employeeRepo.findById(id), DOMAIN, "Id");
		employee.getAccount().setName(form.name());
		employee.setRole(form.role());
		employee.setPhone(form.phone());
		employee.setEmail(form.email());
		return EmployeeInfo.from(employee);
	}

	@Transactional
	public EmployeeInfo updateStatus(int id, EmployeeStatusForm form) {
		var employee = Common.getOne(employeeRepo.findById(id), DOMAIN, "Id");
		employee.getAccount().setStatus(form.status());
		publisher.publishEvent(new EmployeeStatusChangeEvent(employee, form.reason()));
		return EmployeeInfo.from(employee);
	}

	@Transactional
	public EmployeeInfo create(EmployeeForm form) {
		var employee = form.entity();
		publisher.publishEvent(new AccountCreateAndSetEvent(employee, form.name()));
		employeeRepo.save(employee);
		publisher.publishEvent(new EmployeeCreationEvent(employee));
		return EmployeeInfo.from(employee);
	}

	public EmployeeInfoDetails findById(int id) {
		return EmployeeInfoDetails.from(Common.getOne(employeeRepo.findById(id), DOMAIN, "Id"));
	}

	public Page<EmployeeInfo> search(EmployeeSearch search, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
