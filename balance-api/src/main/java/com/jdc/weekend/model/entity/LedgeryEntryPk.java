package com.jdc.weekend.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.jdc.weekend.model.utils.LedgerEntryUtils;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LedgeryEntryPk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private LocalDate issueDate;
	private int seqNumber;
	
	public static LedgeryEntryPk parse(String strId) {
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
		return LedgerEntryUtils.ID_FORMAT.formatted(id.getIssueDate(), id.getSeqNumber());
	}
}
