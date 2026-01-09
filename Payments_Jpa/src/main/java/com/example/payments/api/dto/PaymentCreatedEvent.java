package com.example.payments.api.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentCreatedEvent(
        String eventId,
        String paymentId,
        BigDecimal amount,
        String userEmail,
        Instant occurredAt
    ){
}
