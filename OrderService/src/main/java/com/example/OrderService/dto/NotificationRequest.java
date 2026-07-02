package com.example.OrderService.dto;

import jakarta.validation.constraints.NotBlank;

public record NotificationRequest(
        @NotBlank String recipient,
        @NotBlank String type,
        @NotBlank String message
) {}

