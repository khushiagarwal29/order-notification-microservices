package com.example.OrderService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record OrderRequest(
        @NotBlank String customerName,
        @NotBlank String product,
        @Min(1) Integer quantity,
        @NotNull BigDecimal totalPrice
) {}
