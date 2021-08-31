package com.restaurant.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Message")
public class Message implements Serializable{

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_message")
	private int idMessage;
	
	private Chat chat;
	private User user;
	private OrderUser orderUser;
	
	@Column(name = "message")
	private String message;
	
	@Column(name = "state")
	private boolean state;
	
	public Message(int idMessage, Chat chat, User user, OrderUser orderUser, String message, boolean state) {
		super();
		this.idMessage = idMessage;
		this.chat = chat;
		this.user = user;
		this.orderUser = orderUser;
		this.message = message;
		this.state = state;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getIdMessage() {
		return idMessage;
	}
	public Chat getChat() {
		return chat;
	}
	public User getUser() {
		return user;
	}
	public OrderUser getOrderUser() {
		return orderUser;
	}
	public String getMessage() {
		return message;
	}
	public boolean isState() {
		return state;
	}
	public void setIdMessage(int idMessage) {
		this.idMessage = idMessage;
	}
	public void setChat(Chat chat) {
		this.chat = chat;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setOrderUser(OrderUser orderUser) {
		this.orderUser = orderUser;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	
	private static final long serialVersionUID = -4481438056464980767L;
}
