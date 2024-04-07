package com.jdc.weekend.api.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BalanceInfo(int id, String categoryId, String category, String issuer, LocalDateTime issueAt,
		BigDecimal income, BigDecimal expense, String remark) {

}
