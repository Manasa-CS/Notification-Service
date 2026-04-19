package com.manasa.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @Value("${app.kafka.error-topic")
    private String topic;

    public void sendErrorMessage(String message) {
        kafkaTemplate.send(topic, message);
    }
}
