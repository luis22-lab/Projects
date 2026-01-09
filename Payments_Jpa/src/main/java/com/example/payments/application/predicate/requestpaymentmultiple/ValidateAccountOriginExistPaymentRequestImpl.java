package com.example.payments.application.predicate.requestpaymentmultiple;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.predicate.ValidateRequestPaymentToMultiple;
import com.example.payments.domain.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidateAccountOriginExistPaymentRequestImpl implements ValidateRequestPaymentToMultiple {

        private final AccountRepository accountRepository;

        @Override
        public boolean test(PaymentRequest paymentRequest) {
            return validateOriginExists(paymentRequest);
        }
        private  boolean validateOriginExists(PaymentRequest paymentRequest) {
            return accountRepository.findByIdAccount(paymentRequest.getIdOrigin())
                    .map(account -> account.getIdAccount().equals(paymentRequest.getIdOrigin()))
                    .orElse(false);
        }

        @Override
        public String getErrorMessage() {
            return "Origin Account does not exists";
        }
}
