package com.example.payments.domain.usecase;

import com.example.payments.domain.entity.PaymentRequest;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public interface FindPaymentRequestUseCase extends Function<String, PaymentRequest> {
}
