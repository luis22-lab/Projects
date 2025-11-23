package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)

public class PaymentRequestBean implements PaymentRequest {

    private  final Long id;
    private  final Long idOrigin;
    private  final String requestId;
    private  final List<PaymentRequestDetail>paymentRequestDetails;
    private  final LocalDateTime requestDate;
    private  final PaymentStatusEnum status;

}
