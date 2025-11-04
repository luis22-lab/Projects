package com.example.payments.api.Dto;

import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PaymentRequestDetail(Long id, String origin, String destination,
                                   BigDecimal amount, PaymentMethod paymentMethod, PaymentStatusEnum status,
                                   LocalDateTime valueDate) implements com.example.payments.domain.entity.PaymentRequestDetail {

    public PaymentRequestDetail(Long id, String origin, String destination, BigDecimal amount, PaymentMethod paymentMethod, PaymentStatusEnum status, LocalDateTime valueDate) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.valueDate = valueDate;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getOrigin() {
        return origin;
    }

    @Override
    public String getDestination() {
        return destination;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public PaymentStatusEnum getStatus() {
        return status;
    }

    @Override
    public LocalDateTime getValueDate() {
        return valueDate;
    }
}
