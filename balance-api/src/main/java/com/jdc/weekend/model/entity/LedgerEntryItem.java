package com.jdc.weekend.model.entity;

import java.math.BigDecimal;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
@Embeddable
public class LedgerEntryItem {
	private String item;
	private String id;
	private BigDecimal unitPrice;
}
