package com.example.NotificationService.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(
        String customerName,
        String product,
        Integer quantity,
        BigDecimal totalPrice
) {}
