package com.jdc.weekend.api.input;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.util.StringUtils;

import com.jdc.weekend.model.constant.BalanceType;
import com.jdc.weekend.model.entity.LedgerEntry;
import com.jdc.weekend.model.entity.LedgerEntry_;
import com.jdc.weekend.model.entity.LedgeryEntryPk_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public record LedgerEntrySearch(BalanceType type, LocalDate from, LocalDate to, String keyword) {

	public Predicate[] where(CriteriaBuilder cb, Root<LedgerEntry> root) {
		var list = new ArrayList<Predicate>();
		
		if(null != type) {
			list.add(cb.equal(root.get(LedgerEntry_.category).type(), type));
		}
		
		if(null != from && null != to) {
			list.add(cb.between(root.get(LedgerEntry_.id).get(LedgeryEntryPk_.issueDate), from, to));
		} else {
			if(null != from) {
				list.add(cb.greaterThanOrEqualTo(root.get(LedgerEntry_.id).get(LedgeryEntryPk_.issueDate), from));
			} 
			if(null != to) {
				list.add(cb.lessThanOrEqualTo(root.get(LedgerEntry_.id).get(LedgeryEntryPk_.issueDate), to));
			}
		}
		
		if(StringUtils.hasLength(keyword)) {
			list.add(cb.like(cb.lower(root.get(LedgerEntry_.remark)), "%%%s%%".formatted(keyword.toLowerCase())));
		}
		
		return list.toArray(s -> new Predicate[s]);
	}

}
