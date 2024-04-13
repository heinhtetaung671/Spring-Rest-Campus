package com.jdc.weekend.api.output;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jdc.weekend.model.entity.LedgerEntry;

public record LedgerEntryInfo(String id, int categoryId, String issuer, LocalDate issueAt, int items, BigDecimal amount,
		String remark) {

	public static LedgerEntryInfo from(LedgerEntry entry) {
		return new LedgerEntryInfo(entry.getStringId(), entry.getCategory().getId(),
				entry.getCreatedBy(),
				entry.getIssueAt(), entry.getItems().stream().mapToInt(i -> i.getQuantity()).sum(),
				new BigDecimal(entry.getItems().stream()
						.mapToInt(i -> i.getUnitPrice().multiply(new BigDecimal(i.getQuantity())).intValue()).sum()),
				entry.getRemark());
	}
}
