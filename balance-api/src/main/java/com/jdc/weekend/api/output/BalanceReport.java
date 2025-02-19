package com.jdc.weekend.api.output;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;

public record BalanceReport(BigDecimal lastBalance, Page<BalanceInfo> list) {

}
