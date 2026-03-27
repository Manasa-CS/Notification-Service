package com.manasa.notificationservice.service;

import com.manasa.notificationservice.dto.EmailNotificationDetails;
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
        if(eventMessage.getNotificationMessageDetails() instanceof EmailNotificationDetails emailDetails) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(eventMessage.getNotificationMessageDetails().getRecipient());
            message.setSubject(emailDetails.getSubject());
            message.setText(emailDetails.getBody());
            System.out.println("Email sent to: " + emailDetails.getRecipientEmail() + " with subject: " + emailDetails.getSubject());
            //javaMailSender.send(message);
        }
    }
}
