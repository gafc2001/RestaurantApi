package com.restaurant.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private int idUser;
	
	private RoleUser roleUser;
	
	@Column(name = "name_user")
	private String nameUser;
	
	@Column(name = "profile_picture")
	private String profileUser;
	
	@Column(name = "email_user")
	private String emailUser;
	
	@Column(name = "password_user")
	private String passwordUser;
	public User() {
		super();
	}
	public User(int idUser, RoleUser roleUser, String nameUser, String profileUser, String emailUser,
			String passwordUser) {
		super();
		this.idUser = idUser;
		this.roleUser = roleUser;
		this.nameUser = nameUser;
		this.profileUser = profileUser;
		this.emailUser = emailUser;
		this.passwordUser = passwordUser;
	}
	public int getIdUser() {
		return idUser;
	}
	public RoleUser getRoleUser() {
		return roleUser;
	}
	public String getNameUser() {
		return nameUser;
	}
	public String getProfileUser() {
		return profileUser;
	}
	public String getEmailUser() {
		return emailUser;
	}
	public String getPasswordUser() {
		return passwordUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public void setRoleUser(RoleUser roleUser) {
		this.roleUser = roleUser;
	}
	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}
	public void setProfileUser(String profileUser) {
		this.profileUser = profileUser;
	}
	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}
	
	
	private static final long serialVersionUID = -2992223152926069389L;
	
}
