package com.restaurant.api.controllers;


import com.restaurant.api.payload.request.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ChatController {

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    @MessageMapping("/chat")
    public void sendMessage(@Payload ChatMessage chatMessage){
        messageTemplate.convertAndSend("/queue/messages",chatMessage);
    }
}
