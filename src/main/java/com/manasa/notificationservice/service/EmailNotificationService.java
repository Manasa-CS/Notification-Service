package com.manasa.notificationservice.service;

import com.manasa.notificationservice.dto.EmailNotificationEventMessage;
import com.manasa.notificationservice.dto.NotificationEventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendNotification(NotificationEventMessage eventMessage) {
        System.out.println("Sending email notification to: ");
        if(eventMessage instanceof EmailNotificationEventMessage emailEventMessage) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailEventMessage.getRecipientEmail());
            message.setSubject(emailEventMessage.getSubject());
            message.setText(emailEventMessage.getBody());
            System.out.println("Email sent to: " + emailEventMessage.getRecipientEmail() + " with subject: " + emailEventMessage.getSubject());
            javaMailSender.send(message);
        }
    }
}
