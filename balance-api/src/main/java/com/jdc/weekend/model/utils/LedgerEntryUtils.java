package com.jdc.weekend.model.utils;

import static com.jdc.weekend.model.common.Common.getOne;

import java.time.LocalDate;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.weekend.api.input.LedgerEntryForm;
import com.jdc.weekend.api.input.LedgerEntryFormItem;
import com.jdc.weekend.model.annotation.Utils;
import com.jdc.weekend.model.common.Common;
import com.jdc.weekend.model.constant.DomainNamesForExceptionMsg;
import com.jdc.weekend.model.entity.LedgerEntry;
import com.jdc.weekend.model.entity.LedgeryEntryPk;
import com.jdc.weekend.model.repo.AccountRepo;
import com.jdc.weekend.model.repo.CategoryRepo;
import com.jdc.weekend.model.repo.LedgerEntryRepo;

import lombok.RequiredArgsConstructor;

@Utils
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LedgerEntryUtils {

	private final LedgerEntryRepo ledgerEntryRepo;
	private final CategoryRepo categoryRepo;
	private final AccountRepo accountRepo;

	public static final String ID_FORMAT = "%s:%s";

	public LedgerEntry toEntity(LedgerEntryForm form) {
		var entity = new LedgerEntry();

		var id = new LedgeryEntryPk();
		id.setIssueDate(form.issueDate());
		id.setSeqNumber(ledgerEntryRepo.getNextSeqNumber(form.issueDate()));

		entity.setId(id);
		entity.setIssueAt(LocalDate.now());
		entity.setCategory(Common.getOne(categoryRepo.findById(form.category()), DomainNamesForExceptionMsg.CATEGORY,
				form.category()));
		entity.setRemark(form.remark());
		entity.setItems(form.items().stream().map(LedgerEntryFormItem::toEntity).toList());
		entity.setAccount(getOne(
				accountRepo.findAccountByLoginId(SecurityContextHolder.getContext().getAuthentication().getName()),
				DomainNamesForExceptionMsg.ACCOUNT, SecurityContextHolder.getContext().getAuthentication().getName()));

		return entity;
	}

	public static LedgeryEntryPk fromStringId(String strId) {
		var id = new LedgeryEntryPk();
		var arr = strId.split(":");
		if (arr.length > 0) {
			id.setIssueDate(LocalDate.parse(arr[0]));
			id.setSeqNumber(Integer.parseInt(arr[1]));
			return id;
		} else {
			throw new IllegalArgumentException("Invalid LedgerEntry Id Format.");
		}
	}

	public static String formatId(LedgeryEntryPk id) {
		return ID_FORMAT.formatted(id.getIssueDate(), id.getSeqNumber());
	}

}
