package com.manasa.notificationservice.dto;

import enums.NotificationType;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEventMessage {
    private NotificationType notificationType;
    private NotificationMessageDetails notificationMessageDetails;
}
