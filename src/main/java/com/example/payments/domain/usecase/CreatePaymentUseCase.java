package com.example.payments.domain.usecase;

import com.example.payments.api.Dto.PaymentDto;
import com.example.payments.domain.entity.Payment;

import java.util.function.UnaryOperator;

public interface CreatePaymentUseCase extends UnaryOperator<Payment> {

}
