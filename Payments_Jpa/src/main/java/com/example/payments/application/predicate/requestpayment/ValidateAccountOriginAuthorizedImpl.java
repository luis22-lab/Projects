package com.example.payments.application.predicate.requestpayment;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.predicate.ValidateRequestPaymentToMultiple;
import com.example.payments.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidateAccountOriginAuthorizedImpl implements ValidateRequestPaymentToMultiple {


    private final AccountRepository accountRepository;

    @Override
    public boolean test(PaymentRequest paymentRequest) {
        return validateOriginAuthorized(paymentRequest);
    }

    private  boolean validateOriginAuthorized(PaymentRequest paymentRequest) {
        return accountRepository.findByIdAccount(paymentRequest.getIdOrigin())
                .map(account -> account.getAuthorized().equals(Boolean.TRUE))
                .orElse(false);
    }
    @Override
    public String getErrorMessage() {
        return "la cuenta De Origen No puede realizar pagos";
    }


}

