package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)

public class PaymentRequestDetailBean implements PaymentRequestDetail{

    private final Long id;
    private final String origin;
    private final String destination;
    private final BigDecimal amount;
    private final PaymentMethod paymentMethod;
    private final PaymentStatusEnum status;
    private final LocalDateTime valueDate;

}
