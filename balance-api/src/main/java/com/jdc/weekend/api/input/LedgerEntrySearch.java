package com.jdc.weekend.api.input;

import java.time.LocalDateTime;

import com.jdc.weekend.model.BalanceType;

public record LedgerEntrySearch(BalanceType type, LocalDateTime from, LocalDateTime to, String keyword) {

}
