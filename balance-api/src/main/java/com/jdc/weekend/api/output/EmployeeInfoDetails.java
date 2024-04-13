package com.jdc.weekend.api.output;

import java.util.List;

import com.jdc.weekend.model.entity.Employee;

public record EmployeeInfoDetails(EmployeeInfo info, List<EmployeeHistoryInfo> history) {

	public static EmployeeInfoDetails from(Employee employee) {
		return new EmployeeInfoDetails(EmployeeInfo.from(employee), employee.getEmployeeHistorys().stream().map(EmployeeHistoryInfo::from).toList());
	}
	
}
