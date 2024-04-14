package com.jdc.weekend.api.input;

import com.jdc.weekend.model.constant.BalanceType;
import com.jdc.weekend.model.entity.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryForm(
		@NotNull(message = "Please enter balance type.")
		BalanceType type,
		@NotBlank(message = "Please enter name.")
		String name, String remark) {

	public Category getEntity() {
		var entity = new Category();
		entity.setType(type);
		entity.setName(name);
		entity.setDesciption(remark);
		return entity;
	}
	
}
