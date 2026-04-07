package com.manasa.notificationservice.consumer;

import com.manasa.notificationservice.dto.EmailNotificationEventMessage;
import com.manasa.notificationservice.dto.NotificationEventMessage;
import com.manasa.notificationservice.service.EmailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    private EmailNotificationService emailNotificationService;

    @KafkaListener(    topics = "${app.kafka.topic}",groupId = "${spring.application.kafka.consumer.group-id}", properties = {
            "bootstrap.servers=${spring.application.kafka.bootstrap-servers}",
            "value.deserializer=${spring.application.kafka.consumer.value-deserializer}",
            "spring.json.value.default.type=com.manasa.notificationservice.dto.NotificationEventMessage",
            "spring.json.trusted.packages=com.manasa.notificationservice.dto",
            "spring.json.use.type.headers=false",

    })
    public void consume(NotificationEventMessage message) {

        if(message instanceof EmailNotificationEventMessage emailNotificationEventMessage) {
            System.out.println("Received message mail_id: " + emailNotificationEventMessage.getRecipientEmail());
            emailNotificationService.sendNotification(emailNotificationEventMessage);
        }
    }
}
