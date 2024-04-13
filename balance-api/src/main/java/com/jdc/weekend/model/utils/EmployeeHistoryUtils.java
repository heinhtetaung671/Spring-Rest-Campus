package com.jdc.weekend.model.utils;

import java.time.LocalDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.model.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;
import com.jdc.weekend.model.entity.EmployeeHistory;
import com.jdc.weekend.model.entity.EmployeeHistroyPk;
import com.jdc.weekend.model.repo.EmployeeHistoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeHistoryUtils {

	private final EmployeeHistoryRepo repo;
	
	public EmployeeHistory toHistory(Employee employee, EmployeeChanges employeeChanges) {
		var lastSeqNumber = repo.getNextSeqNumber(employee.getId());
		var employeeHistory = new EmployeeHistory();
		
		employeeHistory.setId(new EmployeeHistroyPk(employee.getId(), lastSeqNumber));
		employeeHistory.setName(employee.getAccount().getName());
		employeeHistory.setEmail(employee.getEmail());
		employeeHistory.setPassword(employee.getAccount().getPassword());
		employeeHistory.setPhone(employee.getPhone());
		employeeHistory.setStatus(employee.getAccount().getStatus());
		employeeHistory.setRole(employee.getRole());
		employeeHistory.setChanges(employeeChanges);
		
		return employeeHistory;
	}
	
	public EmployeeHistory toHistoryForStatusChange(Employee employee, EmployeeChanges employeeChanges, String reason) {
		var employeeHistory = toHistory(employee, employeeChanges);
		employeeHistory.setStatusChangeAt(LocalDate.now());
		employeeHistory.setReason(reason);
		
		return employeeHistory;
	}
}
