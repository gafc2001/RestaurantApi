package com.restaurant.api.payload.request;

import lombok.Data;

import java.util.List;

@Data
public class PaymentRequest {
    private String paymentId;
    private Long amount;
}
