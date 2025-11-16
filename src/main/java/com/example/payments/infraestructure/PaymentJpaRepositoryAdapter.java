package com.example.payments.infraestructure;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.infraestructure.mapper.PaymentEntityMapper;
import com.example.payments.infraestructure.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentJpaRepositoryAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentEntityMapper mapper;

    @Override
    public Optional<Payment> save(Payment payment) {
        return Optional.ofNullable(mapper.mapEntityToBean(paymentJpaRepository.save(mapper.mapBeanToEntity(payment))));
    }

    @Override
    public Optional<Payment> findByRequestId(String requestId) {
        return paymentJpaRepository.findByRequestId(requestId)
                .map(mapper::mapEntityToBean);
    }
}
