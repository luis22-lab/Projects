package com.example.payments.api.dto;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.enums.PaymentMethodEnum;
import com.example.payments.domain.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public record PaymentDto(
        String id,
        String requestId,
        Long idOrigin,
        Long idDestination,
        BigDecimal amount,
        LocalDateTime dateCreated,
        LocalDateTime dateValue,
        PaymentMethodEnum paymentMethod,
        PaymentStatusEnum paymentStatus)
        implements Payment {

    public PaymentDto(String id, String requestId, Long idOrigin, Long idDestination, BigDecimal amount, LocalDateTime dateCreated, LocalDateTime dateValue, PaymentMethodEnum paymentMethod, PaymentStatusEnum paymentStatus) {
        this.id = id;
        this.requestId = requestId;
        this.idOrigin = idOrigin;
        this.idDestination = idDestination;
        this.amount = amount;
        this.dateCreated = dateCreated;
        this.dateValue = dateValue;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getRequestId() {
        return this.requestId;
    }

    @Override
    public Long getIdOrigin() {
        return this.idOrigin;
    }

    @Override
    public Long getIdDestination() {
        return this.idDestination;
    }

    @Override
    public BigDecimal getAmount() {
        return this.amount;
    }

    @Override
    public LocalDateTime getDateCreated() {
        return this.dateCreated;
    }

    @Override
    public LocalDateTime getDateValue() {
        return this.dateValue;
    }

    @Override
    public PaymentMethodEnum getPaymentMethod() {
        return this.paymentMethod;
    }

    @Override
    public PaymentStatusEnum getPaymentStatus() {
        return this.paymentStatus;
    }
}
