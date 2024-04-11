package com.jdc.weekend.model.entity;

import com.jdc.weekend.model.AbstractEntity;
import com.jdc.weekend.model.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
public class Account extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String loginId;
	@Column(nullable = false)
	private Status status;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String password;
	
}
