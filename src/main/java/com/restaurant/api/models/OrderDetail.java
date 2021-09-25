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

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_detail")

@RequiredArgsConstructor
public class OrderDetail implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order_detail")
	@Getter
	@Setter
	private Long idOrderDetail;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@Getter
	@Setter
	@NonNull
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_order")
	@Getter
	@Setter
	@NonNull
	private OrderUser orderUser;
	
	@Column(name = "quantity")
	@Getter
	@Setter
	@NonNull
	private Integer quantity;
	public OrderDetail() {
		super();
	}
	
	
	private static final long serialVersionUID = -5638346545812377702L;
	
}
