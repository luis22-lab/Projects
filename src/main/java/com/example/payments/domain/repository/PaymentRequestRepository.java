package com.example.payments.domain.repository;

import com.example.payments.domain.entity.PaymentRequest;

import java.util.Optional;
import java.util.function.UnaryOperator;

public interface PaymentRequestRepository{

    Optional<PaymentRequest>  findById(Long id);
    Optional<PaymentRequest>  save(PaymentRequest paymentRequest);
}
