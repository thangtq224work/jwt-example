package com.application.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.application.model.EnumRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	@Enumerated(EnumType.STRING)
	private EnumRole role;
	@JsonIgnore
	@ToString.Exclude
	@ManyToMany(mappedBy = "roles")
	private List<Account> accounts;
	public Role(Long id, EnumRole role) {
		super();
		this.id = id;
		this.role = role;
	}
	public Role(Long id) {
		super();
		this.id = id;
	}
	
}
