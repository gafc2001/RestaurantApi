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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "order_user")
public class OrderUser implements Serializable {	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private Long idOrder;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Payment payment;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "orderDetail")
	private List<OrderDetail> orderDetails;
	
	@Column(name = "status_order")
	private String statusOrder;
	
	@Column(name = "desc_order")
	private String description;
	
	@Column(name = "total_order_price")
	private long totalPrice;
	
	public OrderUser() {
		super();
	}
	public OrderUser(User user, String statusOrder, String description, long totalPrice) {
		super();
		this.user = user;
		this.statusOrder = statusOrder;
		this.description = description;
		this.totalPrice = totalPrice;
	}
	public Long getIdOrder() {
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
	public void setIdOrder(Long idOrder) {
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
