package com.example.payments.api.dto;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.entity.PaymentRequestDetail;
import com.example.payments.domain.enums.PaymentStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

public record PaymentRequestDto (Long id,String idempotencyKey,List <PaymentRequestDetail> paymentRequestDetails,LocalDateTime requestDate,PaymentStatusEnum status)implements PaymentRequest{

    public PaymentRequestDto(Long id, String idempotencyKey, List<PaymentRequestDetail> paymentRequestDetails, LocalDateTime requestDate, PaymentStatusEnum status) {
        this.id = id;
        this.idempotencyKey = idempotencyKey;
        this.paymentRequestDetails = paymentRequestDetails;
        this.requestDate = requestDate;
        this.status = status;
    }

    public PaymentRequestDto(PaymentRequest  paymentRequest) {
        this(paymentRequest.getId(),paymentRequest.getIdempotencyKey(),paymentRequest.getPaymentRequestDetails(),paymentRequest.getRequestDate(),paymentRequest.getStatus());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    @Override
    public List<PaymentRequestDetail> getPaymentRequestDetails() {
        return paymentRequestDetails;
    }

    @Override
    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    @Override
    public PaymentStatusEnum getStatus() {
        return status;
    }
}
