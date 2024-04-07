package com.jdc.weekend.model.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class EmployeeHistroyPk implements Serializable{

	private static final long serialVersionUID = 1L;
	private int employeeId;
	private int seqNumber;
	
}
