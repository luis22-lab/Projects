package com.example.payments.application.predicate;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.predicate.ValidateRequestPayment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidateRequestPaymentImpl {

    private final List<ValidateRequestPayment> validators;

    public Optional<String> validateRequestPayment(Payment payment) {
        return validators.stream()
                .filter(validator -> !validator.test(payment))
                .map(ValidateRequestPayment::getErrorMessage)
                .findFirst();
    }

}
