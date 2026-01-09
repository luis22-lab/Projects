package com.example.payments.infraestructure;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.domain.repository.PaymentRequestRepository;
import com.example.payments.infraestructure.mapper.PaymentRequestEntityMapper;
import com.example.payments.infraestructure.repository.PaymentRequestJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PaymentRequestJpaRepositoryAdapter implements PaymentRequestRepository {

    private final PaymentRequestJpaRepository repository;
    private final PaymentRequestEntityMapper mapper;

    @Override
    public Optional<PaymentRequest> findById(Long id) {
        return repository.findById(id)
                .map(mapper::mapPaymentRequestEntityToBean);
    }

    @Override
    public Optional<PaymentRequest> findByRequestId(String requestId) {
        return repository.findByRequestId(requestId)
                .map(mapper::mapPaymentRequestEntityToBean);
    }

    @Override
    public Optional<PaymentRequest> save(PaymentRequest paymentRequest) {
        return Optional.ofNullable(mapper
                .mapPaymentRequestEntityToBean(repository.save(mapper.mapPaymentRequestBeanToEntity((PaymentRequestBean) paymentRequest))));
    }
}
