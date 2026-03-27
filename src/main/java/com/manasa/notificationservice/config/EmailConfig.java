package com.manasa.notificationservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "app.email")
public class EmailConfig {
    private String from;
    private String defaultSubject;
    private String defaultBody;
}
