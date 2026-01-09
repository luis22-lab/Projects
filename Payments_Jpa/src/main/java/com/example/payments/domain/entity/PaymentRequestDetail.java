package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PaymentRequestDetail {

        Long getId();
        String getRequestId();
        Long getOrigin();
        Long getDestinationId();
        PaymentMethod getPaymentMethod();
        LocalDateTime getCreateDate();
        LocalDateTime getDateValue();
        BigDecimal getAmount();
        PaymentStatusEnum getStatus();
}
