package com.restaurant.api.payload;

import lombok.Data;

@Data
public class OrderDetailRequest {
	private Long idproduct;
	private int quantity;
}
