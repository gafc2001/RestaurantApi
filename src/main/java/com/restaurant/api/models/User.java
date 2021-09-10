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
@Table(name = "user")
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
	

	@Column(name = "username")
	@NotBlank
	private String username;
	
	@Column(name = "profile_picture")
	private String profileUser;
	
	@Email
	@Column(name = "email")
	@NotBlank
	private String email;
	
	@Column(name = "password_user")
	@NotBlank
	private String password;
	public User() {
		super();
	}
	
	public User(@NotBlank String username, @Email @NotBlank String email, @NotBlank String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}


	public Long getIdUser() {
		return idUser;
	}



	public Set<RoleUser> getRoles() {
		return roles;
	}



	public String getUsername() {
		return username;
	}



	public String getProfileUser() {
		return profileUser;
	}



	public String getEmail() {
		return email;
	}



	public String getPassword() {
		return password;
	}



	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}



	public void setRoles(Set<RoleUser> roles) {
		this.roles = roles;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public void setProfileUser(String profileUser) {
		this.profileUser = profileUser;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setPassword(String password) {
		this.password = password;
	}







	private static final long serialVersionUID = -2992223152926069389L;
	
	
}
