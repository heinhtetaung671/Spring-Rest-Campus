package com.jdc.weekend.model.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jdc.weekend.api.input.EmployeeForm;
import com.jdc.weekend.api.input.EmployeeSearch;
import com.jdc.weekend.api.input.EmployeeStatusForm;
import com.jdc.weekend.api.output.EmployeeInfo;
import com.jdc.weekend.api.output.EmployeeInfoDetails;

@Service
public class EmployeeService {

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

	public EmployeeInfo create(EmployeeForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public EmployeeInfoDetails findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<EmployeeInfo> search(EmployeeSearch search, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
