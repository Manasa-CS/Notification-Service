package com.manasa.notificationservice.consumer;

import com.manasa.notificationservice.dto.NotificationEventMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @KafkaListener(    topics = "${app.kafka.topic}",groupId = "${spring.application.kafka.consumer.group-id}", properties = {
            "spring.json.value.default.type=com.manasa.notificationservice.dto.NotificationEventMessage",
            "spring.json.trusted.packages=com.manasa.notificationservice.dto",
            "spring.json.use.type.headers=false",
            "value.deserializer=${spring.application.kafka.consumer.value-deserializer}",

    })
    public void consume(NotificationEventMessage message) {
        System.out.println("Received message: " + message);
    }
}
