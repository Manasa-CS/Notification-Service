package com.manasa.notificationservice.consumer;

import com.manasa.notificationservice.dto.NotificationEventMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(    topics = "${app.kafka.topic}",groupId = "${spring.application.kafka.consumer.group-id}")
    public void consume(String message) {
        System.out.println("Received message: " + message);
    }
}
