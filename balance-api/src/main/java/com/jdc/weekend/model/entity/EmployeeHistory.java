package com.jdc.weekend.model.entity;

import java.time.LocalDate;

import com.jdc.weekend.model.AbstractEntity;
import com.jdc.weekend.model.Status;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class EmployeeHistory extends AbstractEntity{

	@EmbeddedId
	private EmployeeHistroyPk id;
	@ManyToOne
	@JoinColumn(name = "employee_id", referencedColumnName = "employee", insertable = false, updatable = false)
	private Employee employee;
	@Column(nullable = false)
	private Status status;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String email;
	private LocalDate statusChangeAt;
	
	private EmployeeChanges changes;
	private String changeBy;
	
}
