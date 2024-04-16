package com.jdc.weekend.api.input;

import java.time.LocalDate;
import java.util.List;

import com.jdc.weekend.model.entity.LedgerEntry;

public record LedgerEntryForm(LocalDate issueDate, int categoryId, String remark, List<LedgerEntryFormItem> items) {

	public static LedgerEntryForm from(LedgerEntry entity) {
		return new LedgerEntryForm(entity.getId().getIssueDate(), entity.getCategory().getId(), entity.getRemark(),
				entity.getItems().stream().map(LedgerEntryFormItem::from).toList());
	}

}
