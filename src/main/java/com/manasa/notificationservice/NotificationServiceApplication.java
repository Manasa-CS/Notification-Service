package com.manasa.notificationservice;

import com.manasa.notificationservice.config.EmailConfig;
import com.manasa.notificationservice.dto.EmailNotificationDetails;
import com.manasa.notificationservice.dto.NotificationEventMessage;
import com.manasa.notificationservice.dto.NotificationMessageDetails;
import com.manasa.notificationservice.service.EmailNotificationService;
import enums.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NotificationServiceApplication {

	@Autowired
	private EmailConfig emailConfig;

	@Autowired
	private EmailNotificationService emailNotificationService;

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner run() {
		return args -> {
			System.out.println("Notification Service is running...");
			System.out.println("Email From: " + emailConfig.getFrom());
			System.out.println("Default Subject: " + emailConfig.getDefaultSubject());
			System.out.println("Default Body: " + emailConfig.getDefaultBody());
			NotificationEventMessage eventMessage = new NotificationEventMessage();
			eventMessage.setNotificationType(NotificationType.EMAIL);
			NotificationMessageDetails emailDetails =new EmailNotificationDetails("test@123", "Test Subject", "This is a test email body.");
			eventMessage.setNotificationMessageDetails(emailDetails);
			emailNotificationService.sendNotification(eventMessage);
		};
	}
}
