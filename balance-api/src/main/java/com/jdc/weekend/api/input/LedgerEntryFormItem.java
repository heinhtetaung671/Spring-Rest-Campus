package com.jdc.weekend.api.input;

import java.math.BigDecimal;

import com.jdc.weekend.model.entity.LedgerEntryItem;

public record LedgerEntryFormItem(String item, int quantity, BigDecimal unitPrice) {

	public static LedgerEntryFormItem from(LedgerEntryItem item) {
		return new LedgerEntryFormItem(item.getItem(), item.getQuantity(), item.getUnitPrice());
	}
	
	public LedgerEntryItem toEntity() {
		var entity = new LedgerEntryItem();
		entity.setItem(item);
		entity.getQuantity();
		entity.setUnitPrice(unitPrice);
		return entity;
	}
}
