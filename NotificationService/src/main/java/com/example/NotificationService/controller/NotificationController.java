package com.example.NotificationService.controller;

import com.example.NotificationService.dto.NotificationRequest;
import com.example.NotificationService.model.Notification;
import com.example.NotificationService.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> receive(@Valid @RequestBody NotificationRequest req) throws InterruptedException {
        log.info("Notification request received.");
        return ResponseEntity.ok(notificationService.process(req));
    }
}
