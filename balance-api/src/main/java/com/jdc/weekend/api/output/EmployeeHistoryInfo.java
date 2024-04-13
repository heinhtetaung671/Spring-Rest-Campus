package com.jdc.weekend.api.output;

import java.time.LocalDateTime;

import com.jdc.weekend.model.EmployeeChanges;
import com.jdc.weekend.model.EmployeeRole;
import com.jdc.weekend.model.Status;
import com.jdc.weekend.model.entity.EmployeeHistory;

public record EmployeeHistoryInfo(
		String name,
		EmployeeRole role,
		Status status, 
		String phone,
		String email,
		EmployeeChanges changes,
		LocalDateTime  changeAt,
		String changeBy) {
	
	public static EmployeeHistoryInfo from(EmployeeHistory employeeHistory) {
		return null;
	}

}
