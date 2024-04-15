package com.jdc.weekend.api.input;

import java.util.ArrayList;

import com.jdc.weekend.model.constant.Role;
import com.jdc.weekend.model.constant.Status;
import com.jdc.weekend.model.entity.Account_;
import com.jdc.weekend.model.entity.Employee;
import com.jdc.weekend.model.entity.Employee_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record EmployeeSearch(Role role, Status status, String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<Employee> root) {
		var list = new ArrayList<Predicate>();

		if (null != role) {
			list.add(cb.equal(root.get(Employee_.role), role));
		}

		if (null != status) {
			list.add(cb.equal(root.get(Employee_.account).get(Account_.status), status));
		}

		if (org.springframework.util.StringUtils.hasLength(keyword)) {
			var lowerAndConcatedKeyword = keyword.toLowerCase().concat("%");
			list.add(cb.or(
					cb.like(cb.lower(root.get(Employee_.account).get(Account_.name)), lowerAndConcatedKeyword),
					cb.like(cb.lower(root.get(Employee_.email)), lowerAndConcatedKeyword),
					cb.like(root.get(Employee_.phone), lowerAndConcatedKeyword)));
		}

		return list.toArray(a -> new Predicate[a]);
	}

}
