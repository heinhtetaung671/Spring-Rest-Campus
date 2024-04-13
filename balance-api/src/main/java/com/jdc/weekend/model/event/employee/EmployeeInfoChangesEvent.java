package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.constant.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;

public class EmployeeInfoChangesEvent extends AbstractEmployeeDataChangesEvent {

	public EmployeeInfoChangesEvent(Employee employee) {
		super(employee, EmployeeChanges.InfoChanges);
	}

	@Override
	public EmployeeChanges getEmployeeChanges() {
		// TODO Auto-generated method stub
		return employeeChanges;
	}

	@Override
	public Employee getEmployee() {
		// TODO Auto-generated method stub
		return employee;
	}

}
