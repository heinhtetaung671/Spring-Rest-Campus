package com.jdc.weekend.model.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.api.input.LedgerEntryForm;
import com.jdc.weekend.api.input.LedgerEntryFormItem;
import com.jdc.weekend.api.input.LedgerEntrySearch;
import com.jdc.weekend.api.output.LedgerEntryDetails;
import com.jdc.weekend.api.output.LedgerEntryInfo;
import com.jdc.weekend.model.common.Common;
import com.jdc.weekend.model.constant.DomainNamesForExceptionMsg;
import com.jdc.weekend.model.entity.LedgerEntry;
import com.jdc.weekend.model.repo.CategoryRepo;
import com.jdc.weekend.model.repo.LedgerEntryRepo;
import com.jdc.weekend.model.utils.LedgerEntryUtils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LedgerEntryService {

	private final LedgerEntryRepo repo;
	private final LedgerEntryUtils utils;
	private final CategoryRepo categoryRepo;

	public LedgerEntryDetails findById(String id) {
		return LedgerEntryDetails.from(Common.getOne(repo.findById(LedgerEntry.fromStringId(id)),
				DomainNamesForExceptionMsg.LEDGER_ENTRY, id));
	}

	@Transactional
	public LedgerEntryInfo create(LedgerEntryForm form) {
		return LedgerEntryInfo.from(repo.save(utils.toEntity(form)));
	}

	@Transactional
	public LedgerEntryInfo update(String id, LedgerEntryForm form) {
		var entity = Common.getOne(repo.findById(LedgerEntry.fromStringId(id)), DomainNamesForExceptionMsg.LEDGER_ENTRY, id);
		
		entity.setCategory(Common.getOne(categoryRepo.findById(form.category()), DomainNamesForExceptionMsg.CATEGORY, form.category()));
		entity.setItems(form.items().stream().map(LedgerEntryFormItem::toEntity).toList());
		entity.setRemark(form.remark());
		return LedgerEntryInfo.from(entity);
	}

	public Page<LedgerEntryInfo> search(LedgerEntrySearch search, int page, int size) {

		return null;
	}

}
