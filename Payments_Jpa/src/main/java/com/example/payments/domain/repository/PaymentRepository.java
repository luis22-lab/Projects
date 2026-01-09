package com.example.payments.domain.repository;

import com.example.payments.domain.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    Optional<Payment> save(Payment payment);
    Optional<Payment> findOneByRequestId(String requestId);
    List<Payment> findByRequestId(String  requestId);
    Optional<Payment> findById(Long paymentId);
}
