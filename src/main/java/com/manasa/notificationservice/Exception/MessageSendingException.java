package com.manasa.notificationservice.Exception;

public class MessageSendingException extends RuntimeException {
    public MessageSendingException(String message) {
        super(message);
    }
    public MessageSendingException(String message, Throwable cause) {
        super(message, cause);
    }
}
