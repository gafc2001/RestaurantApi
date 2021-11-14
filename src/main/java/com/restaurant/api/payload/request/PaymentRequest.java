package com.restaurant.api.payload.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PaymentRequest implements Serializable {
    private String paymentId;
    private Long amount;
}
