package com.example.payments.infraestructure;

import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.infraestructure.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentJpaRepositoryAdapter implements PaymentRepository {

    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Optional<Payment> save(Payment payment) {
        return Optional.ofNullable(paymentMapper.mapPaymentToBean(paymentJpaRepository.save(paymentMapper.mapPaymentToEntity((PaymentBean) payment))));
    }

    @Override
    public Optional<Payment> findByRequestId(String requestId) {
        return paymentJpaRepository.findByRequestId(requestId)
                .map(paymentMapper::mapPaymentToBean);
    }
}
