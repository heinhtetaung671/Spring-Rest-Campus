package com.jdc.weekend.api.output;

import java.math.BigDecimal;
import java.util.List;

import com.jdc.weekend.model.entity.Position;

public record PositionInfoDetails(String code, String name, BigDecimal baseSalary, BigDecimal otPerHour, String remark,
		List<EmployeeInfo> employees) {

	public static PositionInfoDetails from(Position position) {
		return new PositionInfoDetails(position.getCode(), position.getCode(),
				position.getBasicSalary(), position.getOtPerHour(), position.getRemark(),
				position.getEmployees().stream().map(EmployeeInfo::from).toList());
	}

}
