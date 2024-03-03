package com.jdc.weekend.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EmployeeCode {

	@Id
	private String departmentCode;
	private int seqNumber;
	public int next() {
		return ++ seqNumber;
	}

	
	
}
