package com.example.OrderService.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderResponse(
        UUID id, String customerName, String product,
        Integer quantity, BigDecimal totalPrice,
        String status, Instant createdAt
) implements Serializable
{}

