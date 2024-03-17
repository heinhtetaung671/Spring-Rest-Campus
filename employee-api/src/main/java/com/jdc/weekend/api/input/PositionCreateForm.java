package com.jdc.weekend.api.input;

import java.math.BigDecimal;

import com.jdc.weekend.model.entity.Position;
import com.jdc.weekend.model.validators.PositionCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PositionCreateForm(
		@PositionCode(message = "Position code has been already used.")
		@NotBlank(message = "Please enter position code.")
		String code,
		@NotBlank(message = "Please enter name.")
		String name,
		@NotNull(message = "Please enter basic salary.")
		BigDecimal basicSalary,
		@NotNull(message = "Please enter OT Fees per hour.")
		BigDecimal otPerHour,
		String remark
		) {

	public Position entity() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
