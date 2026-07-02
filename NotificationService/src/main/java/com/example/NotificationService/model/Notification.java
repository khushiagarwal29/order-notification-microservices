package com.example.NotificationService.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "notifications")
@Getter
@Setter
@NoArgsConstructor
public class Notification {
    @Id
    private String id;

    private String recipient;
    private String type;
    private String message;
    private String status; // SENT, FAILED
    private Instant createdAt;
}
