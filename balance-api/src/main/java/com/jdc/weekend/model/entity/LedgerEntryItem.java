package com.jdc.weekend.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LedgerEntryItem {
	
	private String quantity;
	private String item;
	private BigDecimal unitPrice;
}
