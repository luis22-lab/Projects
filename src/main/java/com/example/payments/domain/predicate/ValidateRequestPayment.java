package com.example.payments.domain.predicate;

import com.example.payments.domain.entity.Payment;

import java.util.function.Predicate;


public interface ValidateRequestPayment extends Predicate<Payment> {

    String getErrorMessage();

}
