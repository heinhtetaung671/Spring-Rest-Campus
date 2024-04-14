package com.jdc.weekend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(value = AuditingEntityListener.class)
public abstract class AbstractEntity {

	@Column(columnDefinition = "tinyInt default 0")
	private boolean deleted;

	private String createdBy;
	@CreatedDate
	private LocalDateTime createdAt;
	private String modifiedBy;
	@LastModifiedDate
	private LocalDateTime modifiedAt;

}
