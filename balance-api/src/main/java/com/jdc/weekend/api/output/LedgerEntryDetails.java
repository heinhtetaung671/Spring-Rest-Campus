package com.jdc.weekend.api.output;

import java.math.BigDecimal;

import com.jdc.weekend.model.entity.LedgerEntry;

public record LedgerEntryDetails(LedgerEntryInfo info, java.util.List<LedgerEntryInfoItem> items) {

	public int getCount() {
		return items.stream().mapToInt(a -> a.quantity()).sum();
	}
	
	public BigDecimal getTotal() {
		return items.stream().map(a -> a.getTotal()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
	}
	
	public static LedgerEntryDetails from(LedgerEntry entry) {
		return new LedgerEntryDetails(LedgerEntryInfo.from(entry), entry.getItems().stream().map(LedgerEntryInfoItem::from).toList());
	}
	
}
