package com.example.payments.application.usecase;

import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.domain.usecase.GetPaymentUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class GetPaymentUseCaseImpl implements GetPaymentUseCase {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;


    @Override
    public Payment getPayment(String id) {
       return paymentRepository.findPaymentById(id).orElseThrow(() -> new DomainException(422,"No existe el pago"));
    }
}
