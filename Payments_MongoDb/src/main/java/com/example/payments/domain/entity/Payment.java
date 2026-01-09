package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentMethodEnum;
import com.example.payments.domain.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Payment {

    String getId();
    String getRequestId();
    Long getIdOrigin();
    Long getIdDestination();
    BigDecimal getAmount();
    LocalDateTime getDateCreated();
    LocalDateTime getDateValue();
    PaymentMethodEnum getPaymentMethod();
    PaymentStatusEnum getPaymentStatus();

}
