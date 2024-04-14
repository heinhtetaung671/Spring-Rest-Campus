package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.constant.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;

public interface EmployeeDataChangesEvent {

	int getEmployeeId();
	EmployeeChanges getEmployeeChanges();
	
}
