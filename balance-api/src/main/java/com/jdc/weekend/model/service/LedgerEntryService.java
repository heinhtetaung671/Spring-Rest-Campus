package com.jdc.weekend.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.api.input.LedgerEntryForm;
import com.jdc.weekend.api.input.LedgerEntrySearch;
import com.jdc.weekend.api.output.LedgerEntryDetails;
import com.jdc.weekend.api.output.LedgerEntryInfo;
import com.jdc.weekend.model.Common;
import com.jdc.weekend.model.entity.LedgerEntry;
import com.jdc.weekend.model.repo.LedgerEntryRepo;

@Service
@Transactional(readOnly = true)
public class LedgerEntryService {

	@Autowired
	private LedgerEntryRepo repo;
	
	private static final String DOMAIN = "Ledger";
	
	public LedgerEntryDetails findById(String id) {
		return LedgerEntryDetails.from(Common.getOne(repo.findById(LedgerEntry.fromStringId(id)), DOMAIN, "Id"));
	}

	public LedgerEntryInfo create(LedgerEntryForm form) {
		return null;
	}

	public LedgerEntryInfo update(String id, LedgerEntryForm form) {
		return null;
	}

	public Page<LedgerEntryInfo> search(LedgerEntrySearch search, int page, int size) {
		
		return null;
	}

}
