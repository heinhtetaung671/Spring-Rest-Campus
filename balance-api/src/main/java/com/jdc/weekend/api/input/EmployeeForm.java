package com.jdc.weekend.api.input;

import com.jdc.weekend.model.EmployeeRole;

public record EmployeeForm(
		String name,
		EmployeeRole role,
		String phone,
		String email
		) {

}
