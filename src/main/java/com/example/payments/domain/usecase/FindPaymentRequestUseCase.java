package com.example.payments.domain.usecase;

import com.example.payments.domain.entity.PaymentRequest;

import java.util.function.Function;

public interface FindPaymentRequestUseCase extends Function<String, PaymentRequest> {
}
