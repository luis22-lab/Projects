package com.example.payments.api.dto;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.entity.PaymentRequestDetail;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;
import java.util.List;


public record PaymentRequestDto (Long id, String requestId, Long idOrigin, List <PaymentRequestDetailDto> paymentRequestDetails, LocalDateTime requestDate, PaymentStatusEnum status)implements PaymentRequest{

    @JsonCreator
    public PaymentRequestDto(Long id,String requestId,Long idOrigin,List<PaymentRequestDetailDto> paymentRequestDetails,LocalDateTime requestDate,PaymentStatusEnum status) {
        this.id = id;
        this.requestId = requestId;
        this.paymentRequestDetails =paymentRequestDetails;
        this.requestDate = requestDate;
        this.idOrigin = idOrigin;
        this.status = status;
    }

    public PaymentRequestDto(PaymentRequest  paymentRequest) {
        this(paymentRequest.getId(),paymentRequest.getRequestId(),paymentRequest.getIdOrigin(),
                paymentRequest.getPaymentRequestDetails()
                    .stream()
                    .map(PaymentRequestDetailDto::new).toList(),paymentRequest.getRequestDate(),paymentRequest.getStatus());
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getRequestId() {
        return requestId;
    }

    @Override
    public Long getIdOrigin() {
        return idOrigin;
    }

    @Override
    public List<PaymentRequestDetail> getPaymentRequestDetails() {
       return List.copyOf(paymentRequestDetails);
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
