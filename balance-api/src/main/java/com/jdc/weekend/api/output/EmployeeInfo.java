package com.jdc.weekend.api.output;

import com.jdc.weekend.model.constant.EmployeeRole;
import com.jdc.weekend.model.constant.Status;
import com.jdc.weekend.model.entity.Account_;
import com.jdc.weekend.model.entity.Employee;
import com.jdc.weekend.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public record EmployeeInfo(int id, String name, EmployeeRole role, Status status, String phone, String email) {

	public static EmployeeInfo from(Employee entity) {
		return new EmployeeInfo(entity.getId(), entity.getAccount().getName(), entity.getRole(),
				entity.getAccount().getStatus(), entity.getPhone(), entity.getEmail());
	}

	public static void select(CriteriaQuery<EmployeeInfo> cq, Root<Employee> root) {
		cq.multiselect(
				root.get(Employee_.id),
				root.get(Employee_.account).get(Account_.name),
				root.get(Employee_.role),
				root.get(Employee_.account).get(Account_.status),
				root.get(Employee_.phone),
				root.get(Employee_.email)
				);
		
	}

}
