package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRequest {

    Long getId();
    String getIdempotencyKey();
    List<PaymentRequestDetail> getPaymentRequestDetails();
    LocalDateTime getRequestDate();
    PaymentStatusEnum getStatus();

}
