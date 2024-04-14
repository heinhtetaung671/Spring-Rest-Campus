package com.jdc.weekend.model.service;

import java.util.function.Function;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.api.input.EmployeeForm;
import com.jdc.weekend.api.input.EmployeeSearch;
import com.jdc.weekend.api.input.EmployeeStatusForm;
import com.jdc.weekend.api.output.EmployeeInfo;
import com.jdc.weekend.api.output.EmployeeInfoDetails;
import com.jdc.weekend.model.common.Common;
import com.jdc.weekend.model.constant.DomainNamesForExceptionMsg;
import com.jdc.weekend.model.entity.Account_;
import com.jdc.weekend.model.entity.Employee;
import com.jdc.weekend.model.entity.Employee_;
import com.jdc.weekend.model.event.employee.AccountCreateAndSetEvent;
import com.jdc.weekend.model.event.employee.EmployeeCreationEvent;
import com.jdc.weekend.model.event.employee.EmployeeInfoChangesEvent;
import com.jdc.weekend.model.event.employee.EmployeeStatusChangeEvent;
import com.jdc.weekend.model.repo.EmployeeRepo;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepo employeeRepo;
	private final ApplicationEventPublisher publisher;

	public EmployeeForm findByIdForEdit(int id) {
		return EmployeeForm.from(Common.getOne(employeeRepo.findById(id), DomainNamesForExceptionMsg.EMPLOYEE, id));
	}

	@Transactional
	public EmployeeInfo update(int id, EmployeeForm form) {
		var employee = Common.getOne(employeeRepo.findById(id), DomainNamesForExceptionMsg.EMPLOYEE, id);
		employee.getAccount().setName(form.name());
		employee.setRole(form.role());
		employee.setPhone(form.phone());
		employee.setEmail(form.email());

		publisher.publishEvent(new EmployeeInfoChangesEvent(employee.getId()));
		return EmployeeInfo.from(employee);
	}

	@Transactional
	public EmployeeInfo updateStatus(int id, EmployeeStatusForm form) {
		var employee = Common.getOne(employeeRepo.findById(id), DomainNamesForExceptionMsg.EMPLOYEE, id);
		employee.getAccount().setStatus(form.status());
		publisher.publishEvent(new EmployeeStatusChangeEvent(employee.getId(), form.reason()));
		return EmployeeInfo.from(employee);
	}

	@Transactional
	public EmployeeInfo create(EmployeeForm form) {
		var employee = form.entity();
		publisher.publishEvent(new AccountCreateAndSetEvent(employee, form.name()));
		employeeRepo.save(employee);
		publisher.publishEvent(new EmployeeCreationEvent(employee.getId()));
		return EmployeeInfo.from(employee);
	}

	public EmployeeInfoDetails findById(int id) {
		return EmployeeInfoDetails
				.from(Common.getOne(employeeRepo.findById(id), DomainNamesForExceptionMsg.EMPLOYEE, id));
	}

	public Page<EmployeeInfo> search(EmployeeSearch search, int page, int size) {
		return employeeRepo.search(searchFunc(search), countFunc(search), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<EmployeeInfo>> searchFunc(EmployeeSearch search) {
		return cb -> {
			var cq = cb.createQuery(EmployeeInfo.class);
			var root = cq.from(Employee.class);
			EmployeeInfo.select(cq, root);
			cq.where(search.where(cb, root));
			cq.orderBy(cb.asc(root.get(Employee_.account).get(Account_.name)));
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(EmployeeSearch search) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(Employee.class);
			cq.select(cb.count(root.get(Employee_.id)));
			cq.where(search.where(cb, root));
			return cq;
		};
	}

}
