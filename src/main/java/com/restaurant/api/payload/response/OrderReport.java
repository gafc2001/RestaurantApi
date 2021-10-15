package com.restaurant.api.payload.response;

import com.restaurant.api.models.User;
import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
public class OrderReport {

    @NonNull
    private User user;
    @NonNull
    private String description;
    @NonNull
    private long total;
    @NonNull
    private String status;
    @NonNull
    private Timestamp createdAt;

}
