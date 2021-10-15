package com.restaurant.api.payload.response;

import com.restaurant.api.models.Product;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;

@Data
public class MostOrdered {

    @NonNull
    private Product product;
    @NonNull
    private Long total;




}
