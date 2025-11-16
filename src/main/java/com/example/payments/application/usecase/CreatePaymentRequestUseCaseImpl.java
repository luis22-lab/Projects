package com.example.payments.application.usecase;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.repository.PaymentRequestRepository;
import com.example.payments.domain.usecase.CreatePaymentRequestUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatePaymentRequestUseCaseImpl implements CreatePaymentRequestUseCase {

    public static final int status_422 = 422;
    private final PaymentRequestRepository repository;

    @Override
    public PaymentRequest apply(PaymentRequest paymentRequest) {
        final var exists = repository.findByRequestId(paymentRequest.getRequestId());
        exists.ifPresent(existsRequest -> {throw new DomainException(status_422,"Existing Request Payment");});


        return null;
    }
}
