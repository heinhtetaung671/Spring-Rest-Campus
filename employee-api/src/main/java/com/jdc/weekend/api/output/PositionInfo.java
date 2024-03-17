package com.jdc.weekend.api.output;

import java.math.BigDecimal;

import com.jdc.weekend.model.entity.Employee_;
import com.jdc.weekend.model.entity.Position;
import com.jdc.weekend.model.entity.Position_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record PositionInfo(
		String code,
		String name,
		BigDecimal basicSalary,
		BigDecimal otPerHour,
		String remark,
		long employees) {
	
	public static void select(CriteriaBuilder cb, Root<Position> root, CriteriaQuery<PositionInfo> cq){
		
		var employees = root.join(Position_.employees, JoinType.LEFT);
		
		cq.multiselect(
				root.get(Position_.code),
				root.get(Position_.code),
				root.get(Position_.code),
				root.get(Position_.code),
				root.get(Position_.code),
				cb.count(employees.get(Employee_.code))
				);
		
		cq.groupBy(root.get(Position_.code),
				root.get(Position_.code),
				root.get(Position_.code),
				root.get(Position_.code),
				root.get(Position_.code));
		
	}

}
