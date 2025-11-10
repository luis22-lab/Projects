package com.example.payments.application.usecase;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.usecase.CreatePaymentRequestUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreatePaymentRequestImpl implements CreatePaymentRequestUseCase {


   //private  final PaymentRequestRepository repository;

    @Override
    public PaymentRequest apply(PaymentRequest paymentRequest) {

    return null;
    }


}
