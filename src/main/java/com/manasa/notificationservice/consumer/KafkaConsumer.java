package com.manasa.notificationservice.consumer;

import com.manasa.notificationservice.dto.NotificationEventMessage;
import com.manasa.notificationservice.service.EmailNotificationService;
import com.manasa.notificationservice.service.NotificationServiceErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    //@Autowired
    //private EmailNotificationService emailNotificationService;

    @Autowired
    private NotificationServiceErrorHandler notificationServiceErrorHandler;

    @KafkaListener(    topics = "${app.kafka.topic}",groupId = "${spring.kafka.consumer.group-id}", properties = {
            "bootstrap.servers=${spring.kafka.bootstrap-servers}",
            "value.deserializer=${spring.kafka.consumer.value-deserializer}",
            "spring.json.value.default.type=com.manasa.notificationservice.dto.NotificationEventMessage",
            "spring.json.trusted.packages=com.manasa.notificationservice.dto",
            "spring.json.use.type.headers=false",

    })
    public void consume(NotificationEventMessage message) {

       /* if(message instanceof EmailNotificationEventMessage emailNotificationEventMessage) {
            System.out.println("Received message mail_id: " + emailNotificationEventMessage.getRecipientEmail());
            emailNotificationService.sendNotification(emailNotificationEventMessage);
        }*/

        notificationServiceErrorHandler.sendNotificationWithErrorHandling(message);
    }
}
