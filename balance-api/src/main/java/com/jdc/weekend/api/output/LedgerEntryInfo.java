package com.jdc.weekend.api.output;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.jdc.weekend.model.entity.Account_;
import com.jdc.weekend.model.entity.Category_;
import com.jdc.weekend.model.entity.LedgerEntry;
import com.jdc.weekend.model.entity.LedgerEntryItem_;
import com.jdc.weekend.model.entity.LedgerEntry_;
import com.jdc.weekend.model.entity.LedgeryEntryPk_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;

public record LedgerEntryInfo(String id, int categoryId, String issuer, LocalDate issueAt, int items, BigDecimal amount,
		String remark) {

//	public LedgerEntryInfo(LedgeryEntryPk id, int categoryId, String issuer, LocalDate issueAt, int items,
//			BigDecimal amount, String remark) {
//		this(LedgerEntryUtils.formatId(id), categoryId, issuer, issueAt, items, amount, remark);
//	}

	public static LedgerEntryInfo from(LedgerEntry entry) {
		return new LedgerEntryInfo(entry.getStringId(), entry.getCategory().getId(), entry.getAccount().getName(),
				entry.getIssueAt(), entry.getItems().stream().mapToInt(i -> i.getQuantity()).sum(),
				new BigDecimal(entry.getItems().stream()
						.mapToInt(i -> i.getUnitPrice().multiply(new BigDecimal(i.getQuantity())).intValue()).sum()),
				entry.getRemark());
	}

	public static void select(CriteriaBuilder cb, CriteriaQuery<LedgerEntryInfo> cq, Root<LedgerEntry> root) {
		var items = root.join(LedgerEntry_.items, JoinType.LEFT);
		cq.multiselect(
				cb.concat(root.get(LedgerEntry_.id).get(LedgeryEntryPk_.issueDate).as(String.class),
						root.get(LedgerEntry_.id).get(LedgeryEntryPk_.seqNumber).as(String.class)),
				root.get(LedgerEntry_.category).get(Category_.id),
				root.get(LedgerEntry_.createdBy),
				root.get(LedgerEntry_.issueAt),
				cb.sum(items.get(LedgerEntryItem_.quantity)),
				cb.sum(cb.prod(items.get(LedgerEntryItem_.quantity).as(BigDecimal.class),
						items.get(LedgerEntryItem_.unitPrice))),
				root.get(LedgerEntry_.remark)
				);
		
		cq.groupBy(
				root.get(LedgerEntry_.id),
				root.get(LedgerEntry_.category),
				root.get(LedgerEntry_.account).get(Account_.name),
				root.get(LedgerEntry_.issueAt),
				root.get(LedgerEntry_.remark)
				);
		
	}
}
