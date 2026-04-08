package com.manasa.notificationservice.service;

import com.manasa.notificationservice.dto.EmailNotificationEventMessage;
import com.manasa.notificationservice.dto.NotificationEventMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Retryable(value = MailSendException.class, maxRetriesString = "${app.email.retry.max-attempts}", delayString = "${app.email.retry.backoff.delay}")
    public void sendNotification(NotificationEventMessage eventMessage) {
        System.out.println("Sending email notification to: ");
        if(eventMessage instanceof EmailNotificationEventMessage emailEventMessage) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailEventMessage.getRecipientEmail());
            message.setSubject(emailEventMessage.getSubject());
            message.setText(emailEventMessage.getBody());
            try{
                javaMailSender.send(message);
                System.out.println("Email sent to: " + emailEventMessage.getRecipientEmail() + " with subject: " + emailEventMessage.getSubject());
            } catch (MailException e) {
                System.out.println("Failed to send email to: " + emailEventMessage.getRecipientEmail() + " An Exception occured: " + e.getMessage());
            }

        }
    }
}
