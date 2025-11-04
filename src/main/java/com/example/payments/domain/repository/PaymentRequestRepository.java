package com.example.payments.domain.repository;

import com.example.payments.domain.entity.PaymentRequest;

import java.util.function.UnaryOperator;

public interface PaymentRequestRepository extends UnaryOperator<PaymentRequest> {
}
