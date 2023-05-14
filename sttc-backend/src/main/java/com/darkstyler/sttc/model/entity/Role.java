package com.darkstyler.sttc.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "role")
public class Role {

	@Id
	@Column(name = "role_name")
	private String roleName;

	@Column(name = "role_description")
	private String roleDescription;
}
