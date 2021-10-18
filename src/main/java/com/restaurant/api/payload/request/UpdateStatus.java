package com.restaurant.api.payload.request;

import lombok.Data;
import lombok.NonNull;

@Data
public class UpdateStatus {
    @NonNull
    private String status;

}
