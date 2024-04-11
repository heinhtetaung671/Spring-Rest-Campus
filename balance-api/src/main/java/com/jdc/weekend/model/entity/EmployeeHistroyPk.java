package com.jdc.weekend.model.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class EmployeeHistroyPk implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name = "employee_id")
	private int employeeId;
	private int seqNumber;
	
}
