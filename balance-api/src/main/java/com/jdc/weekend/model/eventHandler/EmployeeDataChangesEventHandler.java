package com.jdc.weekend.model.eventHandler;

import static com.jdc.weekend.model.common.Common.getOne;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import com.jdc.weekend.model.constant.DomainNamesForExceptionMsg;
import com.jdc.weekend.model.event.employee.EmployeeCreationEvent;
import com.jdc.weekend.model.event.employee.EmployeeInfoChangesEvent;
import com.jdc.weekend.model.event.employee.EmployeeStatusChangeEvent;
import com.jdc.weekend.model.repo.EmployeeHistoryRepo;
import com.jdc.weekend.model.repo.EmployeeRepo;
import com.jdc.weekend.model.utils.EmployeeHistoryUtils;

import lombok.RequiredArgsConstructor;

@Async
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class EmployeeDataChangesEventHandler {

	private final EmployeeHistoryRepo histRepo;
	private final EmployeeHistoryUtils employeeHistoryUtils;
	private final EmployeeRepo employeeRepo;

	@TransactionalEventListener
	public void handleStatusChangeEvent(EmployeeStatusChangeEvent event) {
		histRepo.save(employeeHistoryUtils.toEntityForStatusChange(getOne(employeeRepo.findById(event.getEmployeeId()), DomainNamesForExceptionMsg.EMPLOYEE, event.getEmployeeId()), event.getEmployeeChanges(),
				event.getReason()));
	}

	@TransactionalEventListener
	public void handleCreationEvent(EmployeeCreationEvent event) {
		histRepo.save(employeeHistoryUtils.toEntity(getOne(employeeRepo.findById(event.getEmployeeId()), DomainNamesForExceptionMsg.EMPLOYEE, event.getEmployeeId()), event.getEmployeeChanges()));
	}

	@TransactionalEventListener
	public void handleInfoChangesEvent(EmployeeInfoChangesEvent event) {
		histRepo.save(employeeHistoryUtils.toEntity(getOne(employeeRepo.findById(event.getEmployeeId()), DomainNamesForExceptionMsg.EMPLOYEE, event.getEmployeeId()), event.getEmployeeChanges()));
	}

}
