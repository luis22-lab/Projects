package com.example.payments.application.predicate;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.predicate.ValidateRequestPayment;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ValidateAmountImpl implements ValidateRequestPayment {

    @Override
    public boolean test(Payment payment) {
        return validateAmount(payment.getAmount());
    }

    private Boolean validateAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public String getErrorMessage(){
        return "La cantidad no puede ser Negativa";
    }

}
