package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.constant.EmployeeChanges;

public interface EmployeeDataChangesEvent {

	int getEmployeeId();
	EmployeeChanges getEmployeeChanges();
	
}
