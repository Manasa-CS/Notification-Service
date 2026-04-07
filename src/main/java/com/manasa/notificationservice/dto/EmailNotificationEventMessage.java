package com.manasa.notificationservice.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeName("EMAIL")
public class EmailNotificationEventMessage extends NotificationEventMessage{
    private String recipientEmail;
    private String subject;
    private String body;
}
