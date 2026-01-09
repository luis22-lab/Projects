package com.example.payments.application.predicate.requestpaymentmultiple;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.predicate.ValidateRequestPayment;
import com.example.payments.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidateAccountOriginPaymentRequestAuthorizedImpl implements ValidateRequestPayment {


    private final AccountRepository accountRepository;

    @Override
    public boolean test(Payment payment) {
        return validateOriginAuthorized(payment);
    }

    private  boolean validateOriginAuthorized(Payment payment) {
        return accountRepository.findByIdAccount(payment.getOrigin())
                .map(account -> account.getAuthorized().equals(Boolean.TRUE))
                .orElse(false);
    }
    @Override
    public String getErrorMessage() {
        return "la cuenta De Origen No puede realizar pagos";
    }
}

