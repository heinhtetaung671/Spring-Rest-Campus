package com.jdc.weekend.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LedgerEntryItem {
	
	private int quantity;
	private String item;
	private BigDecimal unitPrice;
}
