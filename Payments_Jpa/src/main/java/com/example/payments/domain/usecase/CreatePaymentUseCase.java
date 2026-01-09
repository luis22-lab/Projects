package com.example.payments.domain.usecase;

import com.example.payments.domain.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.function.UnaryOperator;

@Component
public interface CreatePaymentUseCase extends UnaryOperator<Payment> {

}
