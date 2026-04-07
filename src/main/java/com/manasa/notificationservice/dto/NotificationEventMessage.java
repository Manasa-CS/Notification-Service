package com.manasa.notificationservice.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import enums.NotificationType;
import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "notificationType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EmailNotificationEventMessage.class, name = "EMAIL"),
       // @JsonSubTypes.Type(value = SmsNotificationDetails.class, name = "SMS")
})
public class NotificationEventMessage {
    private NotificationType notificationType;
}
