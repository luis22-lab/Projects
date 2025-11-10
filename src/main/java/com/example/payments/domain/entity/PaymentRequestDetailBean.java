package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)

public class PaymentRequestDetailBean implements PaymentRequestDetail {

    private final Long idempotencyKey;
    private final String requestId;
    private final String origin;
    private final String destinationId;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime createDate;
    private final LocalDateTime dateValue;
    private final BigDecimal amount;
    private final PaymentStatusEnum status;
}
