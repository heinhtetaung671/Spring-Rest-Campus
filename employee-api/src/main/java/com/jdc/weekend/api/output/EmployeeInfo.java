package com.jdc.weekend.api.output;

import java.time.LocalDate;

import com.jdc.weekend.model.entity.Employee;

public record EmployeeInfo(String code, String name, String phone, LocalDate dateOfBirth, com.jdc.weekend.model.entity.Employee.Gender gender, String email) {

	public static EmployeeInfo from(Employee employee) {
		return new EmployeeInfo(employee.getCode(), employee.getName(), employee.getPhone(), employee.getDateOfBirth(), employee.getGender(), employee.getEmail());
	}
	
}
