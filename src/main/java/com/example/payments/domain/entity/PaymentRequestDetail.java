package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PaymentRequestDetail {

        Long getIdempotencyKey();
        String getOrigin();
        String getDestinationId();
        PaymentMethod getPaymentMethod();
        LocalDateTime getCreateDate();
        LocalDateTime getDateValue();
        BigDecimal getAmount();
        PaymentStatusEnum getStatus();
}
