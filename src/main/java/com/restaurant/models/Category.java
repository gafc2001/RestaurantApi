package com.restaurant.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")

public class Category implements Serializable{


	private static final long serialVersionUID = 4849183077803629224L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_category")
	private int idCategory;
	
	@Column(name = "name_category")
	private String nameCategory;
	
	@Column(name = "desc_category")
	private String description;
	public Category() {
		super();
	}
	public Category(int idCategory, String nameCategory, String description) {
		super();
		this.idCategory = idCategory;
		this.nameCategory = nameCategory;
		this.description = description;
	}
	public int getIdCategory() {
		return idCategory;
	}
	public String getNameCategory() {
		return nameCategory;
	}
	public String getDescription() {
		return description;
	}
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	public void setNameCategory(String nameCategory) {
		this.nameCategory = nameCategory;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
