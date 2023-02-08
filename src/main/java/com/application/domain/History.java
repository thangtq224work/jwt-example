package com.application.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="histories")
@Getter @Setter
@EntityListeners(value = AuditingEntityListener.class)
public class History{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String value;
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
