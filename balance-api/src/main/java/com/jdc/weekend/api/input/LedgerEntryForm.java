package com.jdc.weekend.api.input;

import java.time.LocalDateTime;
import java.util.List;

public record LedgerEntryForm(
		LocalDateTime issueAt, int category, String remark, List<LedgerEntryFormItem> items
		) {

}
