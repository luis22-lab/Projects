package com.example.payments.api.dto;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentDto(
        Long id,
        String requestId,
        Long origin,
        Long destinationId,
        PaymentMethod paymentMethod,
        LocalDateTime createDate,
        LocalDateTime dateValue,
        BigDecimal amount,
        PaymentStatusEnum status
        ) implements Payment
{
    public PaymentDto(Long id, String requestId, Long origin, Long destinationId, PaymentMethod paymentMethod, LocalDateTime createDate, LocalDateTime dateValue, BigDecimal amount, PaymentStatusEnum status) {
        this.id = id;
        this.requestId = requestId;
        this.origin = origin;
        this.destinationId = destinationId;
        this.paymentMethod = paymentMethod;
        this.createDate = createDate;
        this.dateValue = dateValue;
        this.amount = amount;
        this.status = status;
    }

    public PaymentDto(Payment p) {
        this(p.getId(),p.getRequestId(),p.getOrigin(),p.getDestinationId(),p.getPaymentMethod(),p.getCreateDate(),p.getDateValue(),p.getAmount(),p.getStatus());
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
    public Long getOrigin() {
        return origin;
    }

    @Override
    public Long getDestinationId() {
        return destinationId;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    @Override
    public LocalDateTime getDateValue() {
        return dateValue;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public PaymentStatusEnum getStatus() {
        return status;
    }

}
