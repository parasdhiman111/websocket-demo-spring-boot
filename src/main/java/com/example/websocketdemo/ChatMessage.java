package com.example.websocketdemo;

public class ChatMessage {
    private MessageType messageType;
    private String sender;
    private String content;

    public enum MessageType{
        CHAT,
        JOIN,
        LEAVE
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
