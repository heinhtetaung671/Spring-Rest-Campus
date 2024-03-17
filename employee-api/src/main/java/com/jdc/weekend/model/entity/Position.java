package com.jdc.weekend.model.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Position {

	@Id
	private String code;
	private String name;
	private BigDecimal baseSalary;
	private BigDecimal otPerHour;
	private String remark;
	
	@OneToMany(mappedBy = "position")
	private List<Employee> employees;
	
}
