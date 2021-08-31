package com.restaurant.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OrderUser implements Serializable {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private int idOrder;
	
	private User user;
	
	@Column(name = "status_order")
	private String statusOrder;
	
	@Column(name = "desc_order")
	private String description;
	
	@Column(name = "total_order_price")
	private long totalPrice;
	
	public OrderUser() {
		super();
	}
	public OrderUser(int idOrder, User user, String statusOrder, String description, long totalPrice) {
		super();
		this.idOrder = idOrder;
		this.user = user;
		this.statusOrder = statusOrder;
		this.description = description;
		this.totalPrice = totalPrice;
	}
	public int getIdOrder() {
		return idOrder;
	}
	public User getUser() {
		return user;
	}
	public String getStatusOrder() {
		return statusOrder;
	}
	public String getDescription() {
		return description;
	}
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setStatusOrder(String statusOrder) {
		this.statusOrder = statusOrder;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	private static final long serialVersionUID = 2719374845416289773L;
	
}
