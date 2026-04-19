package com.manasa.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceFactory {
    private final EmailNotificationService emailNotificationService;
    //private final smsNotificationErrorHandler smsErrorHandler;

    @Autowired
    public NotificationServiceFactory(@Qualifier("emailNotificationService") EmailNotificationService emailNotificationService){
        //,@Qualifier("smsNotificationErrorHandler") smsNotificationErrorHandler smsErrorHandler) {
        this.emailNotificationService = emailNotificationService;
    }
    public NotificationService getNotificationService(String notificationType) {
        if ("email".equalsIgnoreCase(notificationType)) {
            return emailNotificationService;
        }
        // else if ("sms".equalsIgnoreCase(notificationType)) {
        //     return smsErrorHandler;
        // }
        throw new IllegalArgumentException("Unsupported notification type: " + notificationType);
    }
}
