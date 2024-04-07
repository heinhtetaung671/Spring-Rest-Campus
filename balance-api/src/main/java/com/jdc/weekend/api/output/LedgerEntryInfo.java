package com.jdc.weekend.api.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LedgerEntryInfo(String id, int categoryId, String issuer, LocalDateTime issueAt, int items, BigDecimal amount, String remark) {

}
