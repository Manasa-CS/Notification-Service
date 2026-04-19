package com.manasa.notificationservice.service;

import com.manasa.notificationservice.Exception.MessageSendingException;
import com.manasa.notificationservice.dto.NotificationEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationServiceErrorHandler {
    private final NotificationServiceFactory notificationServiceFactory;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public NotificationServiceErrorHandler(NotificationServiceFactory notificationServiceFactory,
                                            KafkaProducerService kafkaProducerService){
        this.notificationServiceFactory = notificationServiceFactory;
        this.kafkaProducerService = kafkaProducerService;
    }

    public void sendNotificationWithErrorHandling(NotificationEventMessage eventMessage) {
        try {
            notificationServiceFactory.getNotificationService(String.valueOf(eventMessage.getNotificationType())).sendNotification(eventMessage);
        } catch (MessageSendingException e) {
            log.error("Error sending notification for event: {}. Error: {}", eventMessage, e.getMessage());
            kafkaProducerService.sendErrorMessage(e.getMessage()+  eventMessage.toString());
        }
    }
}
