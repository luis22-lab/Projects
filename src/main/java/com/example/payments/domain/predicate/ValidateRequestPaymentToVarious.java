package com.example.payments.domain.predicate;

import com.example.payments.domain.entity.PaymentRequest;

import java.util.function.Predicate;

public interface ValidateRequestPaymentToVarious extends Predicate<PaymentRequest> {

    String getErrorMessage();
}
