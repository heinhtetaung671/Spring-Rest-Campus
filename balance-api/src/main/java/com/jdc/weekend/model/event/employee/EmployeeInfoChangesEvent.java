package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.constant.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;

public class EmployeeInfoChangesEvent extends AbstractEmployeeDataChangesEvent {

	public EmployeeInfoChangesEvent(int employeeId) {
		super(employeeId, EmployeeChanges.InfoChanges);
	}

	@Override
	public EmployeeChanges getEmployeeChanges() {
		// TODO Auto-generated method stub
		return employeeChanges;
	}

	@Override
	public int getEmployeeId() {
		return employeeId;
	}

}
