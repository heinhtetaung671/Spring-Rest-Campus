package com.jdc.weekend.model.service;

import static com.jdc.weekend.model.common.Common.getOne;

import java.util.function.Function;

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
import com.jdc.weekend.model.entity.LedgerEntry_;
import com.jdc.weekend.model.entity.LedgeryEntryPk;
import com.jdc.weekend.model.entity.LedgeryEntryPk_;
import com.jdc.weekend.model.repo.CategoryRepo;
import com.jdc.weekend.model.repo.LedgerEntryRepo;
import com.jdc.weekend.model.utils.LedgerEntryUtils;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LedgerEntryService {

	private final LedgerEntryRepo repo;
	private final LedgerEntryUtils utils;
	private final CategoryRepo categoryRepo;

	public LedgerEntryDetails findById(String id) {
		return LedgerEntryDetails.from(Common.getOne(repo.findById(LedgeryEntryPk.parse(id)),
				DomainNamesForExceptionMsg.LEDGER_ENTRY, id));
	}

	@Transactional
	public LedgerEntryInfo create(LedgerEntryForm form) {
		return LedgerEntryInfo.from(repo.save(utils.toEntity(form)));
	}

	@Transactional
	public LedgerEntryInfo update(String id, LedgerEntryForm form) {
		var entity = getOne(repo.findById(LedgeryEntryPk.parse(id)),
				DomainNamesForExceptionMsg.LEDGER_ENTRY, id);

		entity.setCategory(getOne(categoryRepo.findById(form.categoryId()), DomainNamesForExceptionMsg.CATEGORY,
				form.categoryId()));
		entity.setItems(form.items().stream().map(LedgerEntryFormItem::toEntity).toList());
		entity.setRemark(form.remark());
		return LedgerEntryInfo.from(entity);
	}

	public Page<LedgerEntryInfo> search(LedgerEntrySearch search, int page, int size) {
		return repo.search(queryFunc(search), countFunc(search), page, size);
	}

	private Function<CriteriaBuilder, CriteriaQuery<LedgerEntryInfo>> queryFunc(LedgerEntrySearch search) {
		return cb -> {
			var cq = cb.createQuery(LedgerEntryInfo.class);
			var root = cq.from(LedgerEntry.class);
			LedgerEntryInfo.select(cb, cq, root);
			cq.where(search.where(cb, root));
			cq.orderBy(cb.asc(root.get(LedgerEntry_.id).get(LedgeryEntryPk_.issueDate)));
			return cq;
		};
	}

	private Function<CriteriaBuilder, CriteriaQuery<Long>> countFunc(LedgerEntrySearch search) {
		return cb -> {
			var cq = cb.createQuery(Long.class);
			var root = cq.from(LedgerEntry.class);
			cq.select(cb.count(root.get(LedgerEntry_.id)));
			cq.where(search.where(cb, root));
			return cq;
		};
	}

	public LedgerEntryForm findByIdForEdit(String id) {
		return LedgerEntryForm.from(
				getOne(repo.findById(LedgeryEntryPk.parse(id)), DomainNamesForExceptionMsg.LEDGER_ENTRY, id));
	}

}
