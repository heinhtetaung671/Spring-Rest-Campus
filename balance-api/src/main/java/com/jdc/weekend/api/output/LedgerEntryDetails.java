package com.jdc.weekend.api.output;

import java.math.BigDecimal;

public record LedgerEntryDetails(LedgerEntryInfo info, java.util.List<LedgerEntryInfoItem> items) {

	public int getCount() {
		return items.stream().mapToInt(a -> a.quantity()).sum();
	}
	
	public BigDecimal getTotal() {
		return items.stream().map(a -> a.getTotal()).reduce(BigDecimal.ZERO, (a, b) -> a.add(b));
	}
	
}
