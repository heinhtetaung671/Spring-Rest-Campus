package com.jdc.weekend.model.entity;

import java.time.LocalDate;
import java.util.List;

import com.jdc.weekend.model.AbstractEntity;
import com.jdc.weekend.model.constant.BalanceType;
import com.jdc.weekend.model.utils.LedgerEntryUtils;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
public class LedgerEntry extends AbstractEntity{

	@EmbeddedId
	private LedgeryEntryPk id;
	
	@Column(nullable = false)
	private LocalDate issueAt;
	@ManyToOne(optional = false)
	private Category category;
	private String remark;
	@ManyToOne(optional = false)
	private Account account;
	@Enumerated(EnumType.STRING)
	private BalanceType type;
	@ElementCollection
	private List<LedgerEntryItem> items;
	
	public String getStringId() {
		return LedgerEntryUtils.formatId(id);
	}
		
}
