package com.example.payments.infraestructure;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor

public class PaymentJpaRepositoryAdapter implements PaymentRepository {
    @Override
    public Optional<Payment> save(Payment payment) {
        return null;
    }

    @Override
    public Optional<Payment> findByRequestId(String requestId) {
        return Optional.empty();
    }
}
