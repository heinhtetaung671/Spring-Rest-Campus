package com.jdc.weekend.model.utils;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.jdc.weekend.model.EmployeeChanges;
import com.jdc.weekend.model.entity.Employee;
import com.jdc.weekend.model.entity.EmployeeHistory;
import com.jdc.weekend.model.entity.EmployeeHistroyPk;
import com.jdc.weekend.model.repo.EmployeeHistoryRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeHistoryUtils {

	private final EmployeeHistoryRepo repo;
	
	public EmployeeHistory toHistory(Employee employee, EmployeeChanges employeeChanges) {
		var lastSeqNumber = (int) repo.selectMaxIdSeqNumberByIdEmployeeId(employee.getId());
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
	
	public EmployeeHistory toHistoryForStatusChange(Employee employee, EmployeeChanges employeeChanges) {
		var hist = toHistory(employee, employeeChanges);
		hist.setStatusChangeAt(LocalDate.now());
		return hist;
	}
}
