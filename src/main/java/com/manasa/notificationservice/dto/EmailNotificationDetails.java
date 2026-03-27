package com.manasa.notificationservice.dto;

import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmailNotificationDetails extends NotificationMessageDetails{
    private String recipientEmail;
    private String subject;
    private String body;
}
