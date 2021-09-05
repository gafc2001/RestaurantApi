package com.restaurant.api.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_role")
	private RoleUser roleUser;
	
	@OneToMany(mappedBy = "user")
	private List<OrderUser> orders;
	
	@ManyToMany(mappedBy = "users",cascade = {CascadeType.ALL})
	private List<Chat> chats;
	
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
	public User(RoleUser roleUser, String nameUser, String profileUser, String emailUser,
			String passwordUser) {
		super();
		this.roleUser = roleUser;
		this.nameUser = nameUser;
		this.profileUser = profileUser;
		this.emailUser = emailUser;
		this.passwordUser = passwordUser;
	}
	public Long getIdUser() {
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
	public void setIdUser(Long idUser) {
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
