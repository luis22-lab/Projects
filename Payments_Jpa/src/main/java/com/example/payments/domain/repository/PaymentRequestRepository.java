package com.example.payments.domain.repository;

import com.example.payments.domain.entity.PaymentRequest;

import java.util.Optional;

public interface PaymentRequestRepository{

    Optional<PaymentRequest>  findById(Long id);
    Optional<PaymentRequest> findByRequestId(String requestId);
    Optional<PaymentRequest>  save(PaymentRequest paymentRequest);

}
