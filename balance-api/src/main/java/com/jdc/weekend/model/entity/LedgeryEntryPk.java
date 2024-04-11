package com.jdc.weekend.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class LedgeryEntryPk implements Serializable {

	private static final long serialVersionUID = 1L;
	private LocalDate issueDate;
	private int seqNumber;
}
