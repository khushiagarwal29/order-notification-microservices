package com.example.OrderService.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(
        String customerName,
        String product,
        Integer quantity,
        BigDecimal totalPrice
) {}
