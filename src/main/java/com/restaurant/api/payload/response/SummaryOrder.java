package com.restaurant.api.payload.response;

import lombok.Data;
import lombok.NonNull;

@Data
public class SummaryOrder {
    @NonNull
    private String type;
    @NonNull
    private long porcetange;
    @NonNull
    private boolean status;
    @NonNull
    private long total;

}
