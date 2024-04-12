package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;

public class EmployeeCreationEvent extends AbstractEmployeeDataChangesEvent {

	public EmployeeCreationEvent(Employee employee) {
		super(employee, EmployeeChanges.Creation);
	}

	@Override
	public EmployeeChanges getEmployeeChanges() {
		return super.employeeChanges;
	}

	@Override
	public Employee getEmployee() {
		// TODO Auto-generated method stub
		return employee;
	}
}
