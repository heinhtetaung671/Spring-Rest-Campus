package com.jdc.weekend.api.input;

import com.jdc.weekend.model.constant.EmployeeRole;
import com.jdc.weekend.model.entity.Employee;

public record EmployeeForm(
		String name,
		EmployeeRole role,
		String phone,
		String email
		) {

	public Employee entity() {
		var employee = new Employee();
		employee.setRole(role);
		employee.setPhone(phone);
		employee.setEmail(email);
		return employee;
	}
	
	public static EmployeeForm from(Employee employee) {
		return new EmployeeForm(employee.getAccount().getName(), employee.getRole(), employee.getPhone(), employee.getEmail());
	}
	
}
