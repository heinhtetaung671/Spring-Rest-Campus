package com.jdc.weekend.api.output;

import java.math.BigDecimal;

public record LedgerEntryInfoItem(String item, int quantity, BigDecimal unitPrice) {

	public BigDecimal getTotal() {
		return unitPrice().multiply(unitPrice);
	}
	
}
