package com.manasa.notificationservice.service;

import com.manasa.notificationservice.dto.EmailNotificationEventMessage;
import com.manasa.notificationservice.dto.NotificationEventMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailNotificationService implements NotificationService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    @Retryable(value = MailSendException.class, maxRetriesString = "${app.email.retry.max-attempts}", delayString = "${app.email.retry.backoff.delay}")
    public void sendNotification(NotificationEventMessage eventMessage) {
        log.debug("Attempting to send email notification for event: {}", eventMessage);
        if(eventMessage instanceof EmailNotificationEventMessage emailEventMessage) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailEventMessage.getRecipientEmail());
            message.setSubject(emailEventMessage.getSubject());
            message.setText(emailEventMessage.getBody());
            try{
                javaMailSender.send(message);
                log.info("Email sent successfully to: {} with subject: {}", emailEventMessage.getRecipientEmail(), emailEventMessage.getSubject());
            } catch (MailException e) {
                log.error("Failed to send email to: {} with subject: {}. Error: {}", emailEventMessage.getRecipientEmail(), emailEventMessage.getSubject(), e.getMessage());
                //throw e;
            }

        }
    }
}
