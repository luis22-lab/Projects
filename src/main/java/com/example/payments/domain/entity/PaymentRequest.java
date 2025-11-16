package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRequest {

    Long getId();
    String getRequestId();
    List<PaymentRequestDetail> getPaymentRequestDetails();
    LocalDateTime getRequestDate();
    PaymentStatusEnum getStatus();

}
