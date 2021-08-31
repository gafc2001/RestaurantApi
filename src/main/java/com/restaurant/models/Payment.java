package com.restaurant.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_payment")
	private int idPayment;
	private OrderUser orderUser;
	private PaymentMethod paymentMethod;
	
	@Column(name = "status_payment")
	private boolean statusPayment;
	public Payment() {
		super();
	}
	public Payment(int idPayment, OrderUser orderUser, PaymentMethod paymentMethod, boolean statusPayment) {
		super();
		this.idPayment = idPayment;
		this.orderUser = orderUser;
		this.paymentMethod = paymentMethod;
		this.statusPayment = statusPayment;
	}
	public int getIdPayment() {
		return idPayment;
	}
	public OrderUser getOrderUser() {
		return orderUser;
	}
	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}
	public boolean isStatusPayment() {
		return statusPayment;
	}
	public void setIdPayment(int idPayment) {
		this.idPayment = idPayment;
	}
	public void setOrderUser(OrderUser orderUser) {
		this.orderUser = orderUser;
	}
	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public void setStatusPayment(boolean statusPayment) {
		this.statusPayment = statusPayment;
	}
	
	
	private static final long serialVersionUID = 1222008344275953421L;		
	
}
