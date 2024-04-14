package com.jdc.weekend.api.output;

import java.time.LocalDateTime;

import com.jdc.weekend.model.constant.EmployeeChanges;
import com.jdc.weekend.model.constant.EmployeeRole;
import com.jdc.weekend.model.constant.Status;
import com.jdc.weekend.model.entity.EmployeeHistory;

public record EmployeeHistoryInfo(String name, EmployeeRole role, Status status, String phone, String email,
		EmployeeChanges changes, LocalDateTime changeAt, String changeBy) {

	public static EmployeeHistoryInfo from(EmployeeHistory hist) {
		return new EmployeeHistoryInfo(hist.getName(), hist.getRole(), hist.getStatus(), hist.getPhone(),
				hist.getEmail(), hist.getChanges(), hist.getCreatedAt(), hist.getChangeBy());
	}

}
