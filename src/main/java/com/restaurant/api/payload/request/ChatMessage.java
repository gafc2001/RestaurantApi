package com.restaurant.api.payload.request;

import lombok.Data;

@Data
public class ChatMessage {
    private String message;
    private String sender;
    private String recipient;



}
