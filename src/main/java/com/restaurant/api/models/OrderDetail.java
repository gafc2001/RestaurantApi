package com.restaurant.api.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "order_detail")
public class OrderDetail implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order_detail")
	private Long idOrderDetail;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "id_order")
	private OrderDetail orderDetail;
	
	@Column(name = "quantity")
	private int quantity;
	public OrderDetail() {
		super();
	}
	public OrderDetail(Product product, OrderDetail orderDetail, int quantity) {
		super();
		this.product = product;
		this.orderDetail = orderDetail;
		this.quantity = quantity;
	}
	public Long getIdOrderDetail() {
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
	public void setIdOrderDetail(Long idOrderDetail) {
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
