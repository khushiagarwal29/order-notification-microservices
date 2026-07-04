package com.example.NotificationService.kafka;

import com.example.NotificationService.event.OrderCreatedEvent;
import com.example.NotificationService.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderCreatedConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "orders", groupId = "notification-group")
    public void consume(OrderCreatedEvent event) {

        log.info("Received Kafka Event : {}", event);

        // For now just log it.

    }
}