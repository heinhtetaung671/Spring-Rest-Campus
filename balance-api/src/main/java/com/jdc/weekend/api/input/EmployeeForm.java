package com.jdc.weekend.api.input;

import com.jdc.weekend.model.EmployeeRole;
import com.jdc.weekend.model.entity.Account;
import com.jdc.weekend.model.entity.Employee;

public record EmployeeForm(
		String name,
		EmployeeRole role,
		String phone,
		String email
		) {

	public Employee entity() {
		var account = new Account();
		account.setName(name);
		
		var employee = new Employee();
		employee.setRole(role);
		employee.setPhone(phone);
		employee.setEmail(email);
		return employee;
	}
	
}
