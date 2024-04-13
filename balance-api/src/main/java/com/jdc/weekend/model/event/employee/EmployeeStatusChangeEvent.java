package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.constant.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;

import lombok.Getter;

@Getter
public class EmployeeStatusChangeEvent extends AbstractEmployeeDataChangesEvent{

	private final String reason;
	
	public EmployeeStatusChangeEvent(Employee employee, String reason) {
		super(employee, EmployeeChanges.StatusChanges);
		this.reason = reason;
	}

	@Override
	public EmployeeChanges getEmployeeChanges() {
		return employeeChanges;
	}

	@Override
	public Employee getEmployee() {
		return employee;
	}

}
