package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.constant.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class AbstractEmployeeDataChangesEvent implements EmployeeDataChangesEvent{

	protected final int employeeId;
	protected final EmployeeChanges employeeChanges;
	
}
