package com.jdc.weekend.api.input;

import com.jdc.weekend.model.constant.Role;
import com.jdc.weekend.model.entity.Employee;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeForm(
		@NotBlank(message = "Please enter employee name.")
		String name,
		@NotNull(message = "Please enter employee role.")
		Role role,
		@NotBlank(message = "Please enter employee phone.")
		String phone,
		@NotBlank(message = "Please enter employee email.")
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
