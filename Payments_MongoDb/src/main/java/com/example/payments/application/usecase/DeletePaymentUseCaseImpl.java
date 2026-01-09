package com.example.payments.application.usecase;

import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.domain.usecase.DeletePaymentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeletePaymentUseCaseImpl implements DeletePaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Boolean deletePayment(String id) {
        final var exists = paymentRepository.findPaymentById(id).orElseThrow(() -> new DomainException(422,"Payment not exists"));
        return paymentRepository.deletePaymentById(id);
    }
}
