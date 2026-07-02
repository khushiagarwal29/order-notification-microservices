package com.example.NotificationService.service;

import com.example.NotificationService.dto.NotificationRequest;
import com.example.NotificationService.model.Notification;
import com.example.NotificationService.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository repository;

    public Notification process(NotificationRequest req) throws InterruptedException {
        Notification n = new Notification();
        n.setRecipient(req.recipient());
        n.setType(req.type());
        n.setMessage(req.message());
        n.setCreatedAt(Instant.now());

        // "send" it — just log for now
        log.info("Sending {} notification to {}: {}", req.type(), req.recipient(), req.message());
        n.setStatus("SENT");
        Thread.sleep(5000);
        return repository.save(n);
    }
}
