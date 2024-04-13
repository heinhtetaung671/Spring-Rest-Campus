package com.jdc.weekend.model.repo;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;

import com.jdc.weekend.model.BaseRepo;
import com.jdc.weekend.model.entity.LedgerEntry;
import com.jdc.weekend.model.entity.LedgeryEntryPk;

public interface LedgerEntryRepo extends BaseRepo<LedgerEntry, LedgeryEntryPk>{

	@Query("SELECT COALESCE(MAX(ledger.id.seqNumber), 0) + 1 FROM LedgerEntry ledger WHERE ledger.id.issueDate = ?1")
	int getNextSeqNumber(LocalDate issueDate);
	
}
