package com.jdc.weekend.model.event.employee;

import com.jdc.weekend.model.constant.EmployeeChanges;

import lombok.Getter;

@Getter
public class EmployeeStatusChangeEvent extends AbstractEmployeeDataChangesEvent{

	private final String reason;
	
	public EmployeeStatusChangeEvent(int employeeId, String reason) {
		super(employeeId, EmployeeChanges.StatusChanges);
		this.reason = reason;
	}

	@Override
	public EmployeeChanges getEmployeeChanges() {
		return employeeChanges;
	}

	@Override
	public int getEmployeeId() {
		return employeeId;
	}

}
