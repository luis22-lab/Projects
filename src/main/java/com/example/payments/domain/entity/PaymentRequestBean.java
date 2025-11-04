package com.example.payments.domain.entity;

import com.example.payments.domain.enums.PaymentStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@NoArgsConstructor(force = true)
public class PaymentRequestBean implements PaymentRequest {

    private  final Long id;
    private  final String requestId;
    private  final List<PaymentRequestDetail>paymentRequestDetails;
    private  final LocalDateTime requestDate;
    private  final PaymentStatusEnum status;

}
