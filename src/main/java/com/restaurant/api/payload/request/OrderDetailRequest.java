package com.restaurant.api.payload.request;

import lombok.Data;

@Data
public class OrderDetailRequest {
	private Long idproduct;
	private int quantity;
}
