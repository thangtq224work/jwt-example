package com.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass
public class Audit {
	@Column(name="create_by")
	@CreatedBy
	private String createdBy;
	@Column(name="modified_by")
	@LastModifiedBy
	private String updatedBy;
	@Column
	@CreatedDate
	private Date createTime;
	@Column
	@LastModifiedDate
	private Date updateTime;
}
