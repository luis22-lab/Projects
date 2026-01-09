package com.example.payments.domain.repository;

import com.example.payments.domain.entity.Payment;

import java.util.Optional;

public interface PaymentRepository {

    Optional<Payment> save(Payment payment);
    Optional<Payment> updatePaymentById(Payment payment);
    Boolean deletePaymentById(String id);
    Optional<Payment> findPaymentById(String id);
    Optional<Payment> findPaymentByRequestId(String paymentId);
}
