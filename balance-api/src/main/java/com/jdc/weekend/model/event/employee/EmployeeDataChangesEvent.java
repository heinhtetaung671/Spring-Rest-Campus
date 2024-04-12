package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;

public interface EmployeeDataChangesEvent {

	Employee getEmployee();
	EmployeeChanges getEmployeeChanges();
	
}
