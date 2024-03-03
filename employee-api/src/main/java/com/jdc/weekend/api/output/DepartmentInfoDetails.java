package com.jdc.weekend.api.output;

import com.jdc.weekend.model.entity.Department;

public record DepartmentInfoDetails(
		String code,
		String name,
		String phone,
		String hodName,
		String hodCode,
		String hodPhone,
		String description
		) {

	public static DepartmentInfoDetails from(Department entity) {
		var hod = entity.getHeadOfDepartment();
		
		return new DepartmentInfoDetails(entity.getCode(),
				entity.getName(),
				entity.getPhone(),
				hod == null ? null : hod.getName(),
				hod == null ? null : hod.getCode(),
				hod == null ? null : hod.getPhone(),
				entity.getDescription());
	}
	
}
