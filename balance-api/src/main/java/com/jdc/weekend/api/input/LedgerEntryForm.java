package com.jdc.weekend.api.input;

import java.time.LocalDate;
import java.util.List;

public record LedgerEntryForm(
		LocalDate issueDate, int category, String remark, List<LedgerEntryFormItem> items
		) {

}
