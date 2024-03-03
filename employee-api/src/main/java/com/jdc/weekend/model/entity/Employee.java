package com.jdc.weekend.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Employee {

	@Id
	private String code;

	@Column(nullable = false)
	private String name;
	@ManyToOne
	private Department department;

	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private LocalDate dateOfBirth;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Gender gender;
	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private LocalDate entryDate;
	private LocalDate provationDate;

	public enum Gender {
		Male, Female
	}
}
