package com.jdc.weekend.model.eventHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.jdc.weekend.model.event.employee.EmployeeCreationEvent;
import com.jdc.weekend.model.event.employee.EmployeeInfoChangesEvent;
import com.jdc.weekend.model.event.employee.EmployeeStatusChangeEvent;
import com.jdc.weekend.model.repo.EmployeeHistoryRepo;
import com.jdc.weekend.model.utils.EmployeeHistoryUtils;

import lombok.RequiredArgsConstructor;

@Async
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class EmployeeDataChangesEventHandler {

	private final EmployeeHistoryRepo histRepo;
	private final EmployeeHistoryUtils employeeHistoryUtils;

	@TransactionalEventListener
	public void handleStatusChangeEvent(EmployeeStatusChangeEvent event) {
		histRepo.save(employeeHistoryUtils.toHistoryForStatusChange(event.getEmployee(), event.getEmployeeChanges(),
				event.getReason()));
	}

	@TransactionalEventListener
	public void handleCreationEvent(EmployeeCreationEvent event) {
		histRepo.save(employeeHistoryUtils.toHistory(event.getEmployee(), event.getEmployeeChanges()));
	}

	@TransactionalEventListener
	public void handleInfoChangesEvent(EmployeeInfoChangesEvent event) {
		histRepo.save(employeeHistoryUtils.toHistory(event.getEmployee(), event.getEmployeeChanges()));
	}

}
