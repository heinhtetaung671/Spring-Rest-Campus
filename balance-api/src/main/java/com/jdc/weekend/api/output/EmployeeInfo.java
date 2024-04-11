package com.jdc.weekend.api.output;

import com.jdc.weekend.model.EmployeeRole;
import com.jdc.weekend.model.Status;
import com.jdc.weekend.model.entity.Employee;

public record EmployeeInfo(int id, String name, EmployeeRole role, Status status, String phone, String email) {

	public static EmployeeInfo from(Employee entity) {
		return new EmployeeInfo(entity.getId(), entity.getAccount().getName(), entity.getRole(),
				entity.getAccount().getStatus(), entity.getPhone(), entity.getEmail());
	}

}
