package com.jdc.weekend.api.output;

import com.jdc.weekend.model.entity.Department;
import com.jdc.weekend.model.entity.Department_;
import com.jdc.weekend.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record DepartmentInfo(
		String code,
		String name,
		String hodName,
		String phone,
		long employeeCount
		) {

	public static void select(CriteriaBuilder cb, CriteriaQuery<DepartmentInfo> cq, Root<Department> root) {
		var employee = root.join(Department_.headOfDepartment, JoinType.LEFT);
		var employees = root.join(Department_.employees, JoinType.LEFT);
		cq.multiselect(
				root.get(Department_.code),
				root.get(Department_.name),
				employee.get(Employee_.name),
				root.get(Department_.phone),
				cb.count(employees.get(Employee_.code))
				);
		
		cq.groupBy(root.get(Department_.code),
				root.get(Department_.name),
				employee.get(Employee_.name),
				root.get(Department_.phone));
	}
}
