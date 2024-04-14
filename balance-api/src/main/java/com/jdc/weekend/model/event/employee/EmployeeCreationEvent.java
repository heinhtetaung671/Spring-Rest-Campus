package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.constant.EmployeeChanges;

public class EmployeeCreationEvent extends AbstractEmployeeDataChangesEvent {

	public EmployeeCreationEvent(int employeeId) {
		super(employeeId, EmployeeChanges.Creation);
	}

	@Override
	public EmployeeChanges getEmployeeChanges() {
		return super.employeeChanges;
	}

	@Override
	public int getEmployeeId() {
		return employeeId;
	}
}
