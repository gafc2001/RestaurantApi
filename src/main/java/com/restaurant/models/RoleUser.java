package com.restaurant.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role_user")
public class RoleUser implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_role")
	private int idRole;
	
	@Column(name = "name_role")
	private String nameRole;
	
	public RoleUser() {
		super();
	}
	public RoleUser(int idRole, String nameRole) {
		super();
		this.idRole = idRole;
		this.nameRole = nameRole;
	}
	
	public int getIdRole() {
		return idRole;
	}
	public String getNameRole() {
		return nameRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}
	
	private static final long serialVersionUID = -5115978469393994501L;
}
