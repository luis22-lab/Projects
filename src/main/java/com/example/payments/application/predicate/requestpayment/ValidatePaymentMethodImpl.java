package com.example.payments.application.predicate.requestpayment;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.predicate.ValidateRequestPayment;
import com.example.payments.domain.repository.AccountRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ValidatePaymentMethodImpl implements ValidateRequestPayment {

    private final AccountRepository accountRepository;

    @Override
    public boolean test(Payment payment) {
       return validateAccountPaymentMethod(payment);
    }

    private Boolean validateAccountPaymentMethod(Payment payment) {
        return accountRepository.findByIdAccount(payment.getDestinationId())
                .map(account -> account.getPaymentMethod().equals(payment.getPaymentMethod()))
                .orElse(false);
    }
    @Override
    public String getErrorMessage() {
        return "Método de pago no admitido";
    }
}
