package com.jdc.weekend.api.output;

import com.jdc.weekend.model.EmployeeRole;
import com.jdc.weekend.model.Status;

public record EmployeeInfo(
		int id,
		String name,
		EmployeeRole role,
		Status status, 
		String phone,
		String email
		) {

}
