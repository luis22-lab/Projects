package com.example.payments.domain.usecase;

import com.example.payments.domain.entity.Payment;

public interface UpdatePaymentUseCase {

    Payment updatePayment(String id, Payment payment);
}
