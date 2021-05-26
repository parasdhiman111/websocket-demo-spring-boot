package com.example.websocketdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger LOGGER= LoggerFactory.getLogger(WebSocketEventListener.class);

    @Autowired
    private SimpMessageSendingOperations messageSendingOperations;

    @EventListener
    public void handleWebSocketConnectionListener(SessionConnectedEvent event)
    {
        LOGGER.info("Received a new web socket connection");
    }


    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event)
    {
        StompHeaderAccessor headerAccessor=StompHeaderAccessor.wrap(event.getMessage());
        String username= (String) headerAccessor.getSessionAttributes().get("username");
        if(username!=null)
        {
            LOGGER.info("User Disconnected : "+username);
            ChatMessage chatMessage=new ChatMessage();
            chatMessage.setSender(username);
            chatMessage.setMessageType(ChatMessage.MessageType.LEAVE);
            messageSendingOperations.convertAndSend("/topic/public",chatMessage);
        }

    }
}
