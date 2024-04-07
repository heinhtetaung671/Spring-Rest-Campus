package com.jdc.weekend.api.output;

import com.jdc.weekend.model.entity.Employee;

public record EmployeeInfo(String code, String name, String phone, String email, String position, String department) {

	public static EmployeeInfo from(Employee employee) {
		return new EmployeeInfo(employee.getCode(), employee.getName(), employee.getPhone(), employee.getEmail(), employee.getPosition().getCode(), employee.getDepartment().getCode());
	}
	
}
