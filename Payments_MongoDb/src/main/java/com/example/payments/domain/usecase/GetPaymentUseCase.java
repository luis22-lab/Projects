package com.example.payments.domain.usecase;

import com.example.payments.domain.entity.Payment;

public interface GetPaymentUseCase {
    Payment getPayment(String id);
}
