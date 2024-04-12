package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;

public class EmployeeStatusChangeEvent extends AbstractEmployeeDataChangesEvent{

	public EmployeeStatusChangeEvent(Employee employee) {
		super(employee, EmployeeChanges.StatusChanges);
	}

	@Override
	public EmployeeChanges getEmployeeChanges() {
		return employeeChanges;
	}

	@Override
	public Employee getEmployee() {
		// TODO Auto-generated method stub
		return employee;
	}

}
