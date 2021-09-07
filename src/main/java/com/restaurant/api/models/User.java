package com.restaurant.api.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "nameUser"),
				@UniqueConstraint(columnNames = "email")
		})
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long idUser;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "role_user", 
				joinColumns = @JoinColumn(name = "id_user"), 
				inverseJoinColumns = @JoinColumn(name = "id_role"))
	private Set<RoleUser> roles = new HashSet<>();
	
	@OneToMany(mappedBy = "user")
	private List<OrderUser> orders;
	
	@ManyToMany(mappedBy = "users",cascade = {CascadeType.ALL})
	private List<Chat> chats;
	
	@Column(name = "name_user")
	@NotBlank
	private String nameUser;
	
	@Column(name = "profile_picture")
	private String profileUser;
	
	@Email
	@Column(name = "email_user")
	@NotBlank
	private String emailUser;
	
	@Column(name = "password_user")
	@NotBlank
	private String passwordUser;
	public User() {
		super();
	}
	
	
	
	public User(Long idUser, Set<RoleUser> roles, String nameUser,
			String profileUser, @Email String emailUser, String passwordUser) {
		super();
		this.idUser = idUser;
		this.roles = roles;
		this.nameUser = nameUser;
		this.profileUser = profileUser;
		this.emailUser = emailUser;
		this.passwordUser = passwordUser;
	}

	

	public Long getIdUser() {
		return idUser;
	}



	public Set<RoleUser> getRoles() {
		return roles;
	}



	public List<OrderUser> getOrders() {
		return orders;
	}



	public List<Chat> getChats() {
		return chats;
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



	public void setRoles(Set<RoleUser> roles) {
		this.roles = roles;
	}



	public void setOrders(List<OrderUser> orders) {
		this.orders = orders;
	}



	public void setChats(List<Chat> chats) {
		this.chats = chats;
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
