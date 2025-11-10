package com.example.payments.application.usecase;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.usecase.FindPaymentRequestUseCase;
import org.springframework.stereotype.Component;

@Component
public class FindPaymentRequestImpl implements FindPaymentRequestUseCase {
    @Override
    public PaymentRequest apply(String aLong) {
        return null;
    }
}
