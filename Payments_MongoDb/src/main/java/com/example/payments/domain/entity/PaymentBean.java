package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentMethodEnum;
import com.example.payments.domain.enums.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class PaymentBean implements Payment{

    private final String id;
    private final String requestId;
    private final Long idOrigin;
    private final Long idDestination;
    private final BigDecimal amount;
    private final LocalDateTime dateCreated;
    private final LocalDateTime dateValue;
    private final PaymentMethodEnum paymentMethod;
    private final PaymentStatusEnum paymentStatus;
}
