package com.example.payments.infraestructure;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.repository.PaymentRequestRepository;

public class PaymentRequestJpaRepositoryAdapter implements PaymentRequestRepository {
    @Override
    public PaymentRequest apply(PaymentRequest paymentRequest) {
        return null;
    }
}
