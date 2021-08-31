package com.restaurant.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class OrderDetail implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order_detail")
	private int idOrderDetail;
	
	private Product product;
	private OrderDetail orderDetail;
	
	@Column(name = "quantity")
	private int quantity;
	public OrderDetail() {
		super();
	}
	public OrderDetail(int idOrderDetail, Product product, OrderDetail orderDetail, int quantity) {
		super();
		this.idOrderDetail = idOrderDetail;
		this.product = product;
		this.orderDetail = orderDetail;
		this.quantity = quantity;
	}
	public int getIdOrderDetail() {
		return idOrderDetail;
	}
	public Product getProduct() {
		return product;
	}
	public OrderDetail getOrderDetail() {
		return orderDetail;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setIdOrderDetail(int idOrderDetail) {
		this.idOrderDetail = idOrderDetail;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public void setOrderDetail(OrderDetail orderDetail) {
		this.orderDetail = orderDetail;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	private static final long serialVersionUID = -5638346545812377702L;
	
}
