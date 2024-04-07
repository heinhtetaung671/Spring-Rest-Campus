package com.jdc.weekend.model.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.jdc.weekend.api.input.LedgerEntryForm;
import com.jdc.weekend.api.input.LedgerEntrySearch;
import com.jdc.weekend.api.output.LedgerEntryDetails;
import com.jdc.weekend.api.output.LedgerEntryInfo;

@Service
public class LedgerEntryService {

	public LedgerEntryDetails findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public LedgerEntryInfo create(LedgerEntryForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public LedgerEntryInfo update(String id, LedgerEntryForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public Page<LedgerEntryInfo> search(LedgerEntrySearch search, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
