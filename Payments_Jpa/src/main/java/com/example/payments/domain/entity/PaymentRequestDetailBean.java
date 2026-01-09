package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonTypeName("PaymentRequestDetail")
public class PaymentRequestDetailBean implements PaymentRequestDetail {

    private final Long id;
    private final String requestId;
    private final Long origin;
    private final Long destinationId;
    private final PaymentMethod paymentMethod;
    private final LocalDateTime createDate;
    private final LocalDateTime dateValue;
    private final BigDecimal amount;
    private final PaymentStatusEnum status;
}
