package com.example.payments.domain.usecase;

import com.example.payments.domain.entity.PaymentRequest;

import java.util.function.UnaryOperator;

public interface CreatePaymentRequestUseCase extends UnaryOperator<PaymentRequest> {
}
