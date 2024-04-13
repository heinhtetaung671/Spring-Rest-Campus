package com.jdc.weekend.model.entity;

import java.time.LocalDate;
import java.util.List;

import com.jdc.weekend.model.AbstractEntity;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
	
	@ElementCollection
	private List<LedgerEntryItem> items;

	public static final String ID_FORMAT = "%s:%s";
	
	public String getStringId() {
		return "%s:%s".formatted(id.getIssueDate(), id.getSeqNumber());
	}
	
	public static LedgeryEntryPk fromStringId(String strId) {
		var id = new LedgeryEntryPk();
		var arr = strId.split(":");
		if(arr.length > 0) {
			id.setIssueDate(LocalDate.parse(arr[0]));
			id.setSeqNumber(Integer.parseInt(arr[1]));
			return id;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
}
