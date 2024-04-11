package com.jdc.weekend.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.api.input.EmployeeForm;
import com.jdc.weekend.api.input.EmployeeSearch;
import com.jdc.weekend.api.input.EmployeeStatusForm;
import com.jdc.weekend.api.output.EmployeeInfo;
import com.jdc.weekend.api.output.EmployeeInfoDetails;
import com.jdc.weekend.model.repo.AccountRepo;
import com.jdc.weekend.model.repo.EmployeeRepo;

@Service
@Transactional(readOnly = true)
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;
	private AccountRepo accountRepo;
	
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
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public EmployeeInfo create(EmployeeForm form) {
		var employee = form.entity();
		var account = form.entity().getAccount();
		
		accountRepo.save(account);
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
