package com.manasa.notificationservice.service;

import com.manasa.notificationservice.Exception.MessageSendingException;
import com.manasa.notificationservice.dto.NotificationEventMessage;

public interface NotificationService {
    public void sendNotification(NotificationEventMessage eventMessage) throws MessageSendingException;
}
