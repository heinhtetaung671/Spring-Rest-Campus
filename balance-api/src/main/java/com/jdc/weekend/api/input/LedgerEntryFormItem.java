package com.jdc.weekend.api.input;

import java.math.BigDecimal;

import com.jdc.weekend.model.entity.LedgerEntryItem;

public record LedgerEntryFormItem(String item, int quantity, BigDecimal unitPrice) {

	public LedgerEntryItem toEntity() {
		var entity = new LedgerEntryItem();
		entity.setItem(item);
		entity.getQuantity();
		entity.setUnitPrice(unitPrice);
		return entity;
	}
}
