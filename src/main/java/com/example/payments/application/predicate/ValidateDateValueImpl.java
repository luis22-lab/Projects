package com.example.payments.application.predicate;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.predicate.ValidateRequestPayment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidateDateValueImpl implements ValidateRequestPayment {

    @Override
    public boolean test(Payment payment) {
        return isDateValid(payment.getDateValue());
    }
    @Override
    public String getErrorMessage() {
        return "La Fecha valor no puede ser Anterior a la actual";
    }

    private Boolean isDateValid(LocalDateTime date) {
        return date.isBefore(LocalDateTime.now());
    }

}
