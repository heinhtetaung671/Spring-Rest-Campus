package com.jdc.weekend.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Embeddable
@EqualsAndHashCode
public class LedgerEntryItem {
	
	private int quantity;
	private String item;
	private BigDecimal unitPrice;
}
