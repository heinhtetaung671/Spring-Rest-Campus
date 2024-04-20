package com.jdc.weekend.api.input;

import java.time.LocalDate;
import java.util.List;

import com.jdc.weekend.model.entity.LedgerEntry;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record LedgerEntryForm(
		@NotNull(message = "Please enter issueDate.")
		LocalDate issueDate,
		@Min(value = 1, message = "Please select category.")
		int categoryId, String remark, List<LedgerEntryFormItem> items) {

	public static LedgerEntryForm from(LedgerEntry entity) {
		return new LedgerEntryForm(entity.getId().getIssueDate(), entity.getCategory().getId(), entity.getRemark(),
				entity.getItems().stream().map(LedgerEntryFormItem::from).toList());
	}

}
