package com.restaurant.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "product")
public class Product implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_product")
	private int idProduct;
	
	private Category category;
	private String nameProduct;
	
	@Column(name = "price_product")
	private long priceProduct;
	
	@Column(name = "available_product")
	private boolean availableProduct;
	
	@Column(name = "desc_product")
	private String description;
	
	
	public Product() {
		super();
	}

	public Product(int idProduct, Category category, String nameProduct, long priceProduct, boolean availableProduct,
			String description) {
		super();
		this.idProduct = idProduct;
		this.category = category;
		this.nameProduct = nameProduct;
		this.priceProduct = priceProduct;
		this.availableProduct = availableProduct;
		this.description = description;
	}


	public int getIdProduct() {
		return idProduct;
	}


	public Category getCategory() {
		return category;
	}


	public String getNameProduct() {
		return nameProduct;
	}


	public long getPriceProduct() {
		return priceProduct;
	}


	public boolean isAvailableProduct() {
		return availableProduct;
	}


	public String getDescription() {
		return description;
	}


	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public void setNameProduct(String nameProduct) {
		this.nameProduct = nameProduct;
	}


	public void setPriceProduct(long priceProduct) {
		this.priceProduct = priceProduct;
	}


	public void setAvailableProduct(boolean availableProduct) {
		this.availableProduct = availableProduct;
	}


	public void setDescription(String description) {
		this.description = description;
	}

	
	private static final long serialVersionUID = -4546287916631672894L;	
	
}
