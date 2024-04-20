package com.jdc.weekend.api.input;

import java.math.BigDecimal;

import com.jdc.weekend.model.entity.LedgerEntryItem;

import jakarta.validation.constraints.NotBlank;

public record LedgerEntryFormItem(
		@NotBlank(message = "Please enter item name")
		String name, int quantity, BigDecimal unitPrice) {

	public static LedgerEntryFormItem from(LedgerEntryItem item) {
		return new LedgerEntryFormItem(item.getItem(), item.getQuantity(), item.getUnitPrice());
	}
	
	public LedgerEntryItem toEntity() {
		var entity = new LedgerEntryItem();
		entity.setItem(name);
		entity.setQuantity(quantity);
		entity.setUnitPrice(unitPrice);
		return entity;
	}
}
