package com.example.payments.application.usecase;

import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.domain.usecase.CreatePaymentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatePaymentUseCaseImpl implements CreatePaymentUseCase {

    private final Integer status_422 = 422;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    @Override
    public Payment createPayment(Payment payment) {
        final var exists = paymentRepository.findPaymentByRequestId(payment.getRequestId());
        exists.ifPresent(existsPayment -> {throw new DomainException(status_422,"Payment existente");});
        return  paymentRepository.save(payment).orElseThrow(() -> new DomainException(status_422,"Error al guardar pago"));
    }
}
