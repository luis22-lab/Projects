package com.example.payments.domain.entity;

import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface Payment {

    Long getId();
    String getRequestId();
    String getOrigin();
    Long getDestinationId();
    PaymentMethod getPaymentMethod();
    LocalDateTime getCreateDate();
    LocalDateTime getDateValue();
    BigDecimal getAmount();
    PaymentStatusEnum getPaymentStatusEnum();

}