package com.jdc.weekend.model.utils;

import java.time.LocalDate;

import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.annotation.Utils;
import com.jdc.weekend.model.constant.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;
import com.jdc.weekend.model.entity.EmployeeHistory;
import com.jdc.weekend.model.entity.EmployeeHistroyPk;
import com.jdc.weekend.model.repo.EmployeeHistoryRepo;

import lombok.RequiredArgsConstructor;

@Utils
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeHistoryUtils {

	private final EmployeeHistoryRepo repo;
	
	public EmployeeHistory toEntity(Employee employee, EmployeeChanges employeeChanges) {
		var lastSeqNumber = repo.getNextSeqNumber(employee.getId());
		var entity = new EmployeeHistory();
		
		entity.setId(new EmployeeHistroyPk(employee.getId(), lastSeqNumber));
		entity.setName(employee.getAccount().getName());
		entity.setEmail(employee.getEmail());
		entity.setPassword(employee.getAccount().getPassword());
		entity.setPhone(employee.getPhone());
		entity.setStatus(employee.getAccount().getStatus());
		entity.setRole(employee.getRole());
		entity.setChanges(employeeChanges);
		
		employee.getEmployeeHistorys().add(entity);
		entity.setEmployee(employee);
		return entity;
	}
	
	public EmployeeHistory toEntityForStatusChange(Employee employee, EmployeeChanges employeeChanges, String reason) {
		var entity = toEntity(employee, employeeChanges);
		entity.setStatusChangeAt(LocalDate.now());
		entity.setReason(reason);
		
		employee.getEmployeeHistorys().add(entity);
		return entity;
	}
}
