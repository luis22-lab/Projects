package com.example.payments.infraestructure;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.infraestructure.mapper.PaymentEntityMapper;
import com.example.payments.infraestructure.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
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
    public Optional<Payment> findOneByRequestId(String requestId) {
        return paymentJpaRepository.findOneByRequestId(requestId)
                .map(mapper::mapEntityToBean);
    }

    @Override
    public List<Payment> findByRequestId(String requestId) {
        return paymentJpaRepository.findByRequestId(requestId)
                .stream()
                .map(mapper::mapEntityToBean)
                .map(p -> (Payment) p)
                .toList();
    }

    @Override
    public Optional<Payment> findById(Long paymentId) {
        return paymentJpaRepository.findById(paymentId).map( mapper::mapEntityToBean);
    }

}
