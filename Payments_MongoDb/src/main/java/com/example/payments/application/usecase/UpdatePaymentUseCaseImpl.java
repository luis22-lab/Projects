package com.example.payments.application.usecase;

import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.domain.usecase.UpdatePaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdatePaymentUseCaseImpl implements UpdatePaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Payment updatePayment(String id,Payment payment) {
        final var exists = paymentRepository.findPaymentById(id).orElseThrow(() -> new DomainException(422,"Payment Not exists"));
        return  paymentRepository.updatePaymentById(payment).orElseThrow(() -> new DomainException(422,"Error creating Payment"));
    }
}
