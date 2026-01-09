package com.example.payments.application.usecase;

import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.application.mapper.PaymentRequestMapper;
import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.domain.repository.PaymentRequestRepository;
import com.example.payments.domain.usecase.FindPaymentRequestUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FindPaymentRequestUseCaseImpl implements FindPaymentRequestUseCase {

    private static final int status_422 = 422;
    private final PaymentRequestRepository paymentRequestRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentRequestMapper paymentRequestMapper;

    @Override
    public PaymentRequest apply(String requestId) {
        final var exists  = paymentRequestRepository.findByRequestId(requestId).orElseThrow(() -> new DomainException(status_422,"Payment Request not found"));
        final var payments = paymentRepository.findByRequestId(exists.getRequestId());
        final var details = payments.stream().map(paymentMapper::mapPaymentToPaymentRequestDetail).toList();
        return paymentRequestMapper.mapToBeanRequest_Detail(exists,details);
    }
}
