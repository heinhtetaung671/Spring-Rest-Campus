package com.jdc.weekend.api.output;

import java.math.BigDecimal;

import com.jdc.weekend.model.entity.LedgerEntryItem;

public record LedgerEntryInfoItem(String item, int quantity, BigDecimal unitPrice) {

	public BigDecimal getTotal() {
		return unitPrice().multiply(new BigDecimal(quantity));
	}

	public static LedgerEntryInfoItem from(LedgerEntryItem item) {
		return new LedgerEntryInfoItem(item.getItem(), item.getQuantity(), item.getUnitPrice());
	}
	
}
