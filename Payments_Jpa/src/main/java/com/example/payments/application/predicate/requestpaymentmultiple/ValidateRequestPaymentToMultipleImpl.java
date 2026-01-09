package com.example.payments.application.predicate.requestpaymentmultiple;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.predicate.ValidateRequestPaymentToMultiple;
import com.example.payments.domain.repository.AccountRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Data
public class ValidateRequestPaymentToMultipleImpl {

    private final List<ValidateRequestPaymentToMultiple> validators;
    private final AccountRepository accountRepository;
    public Optional<String> validateRequestPaymentToMultiple(PaymentRequest paymentRequest) {
        final var accounts  = paymentRequest.getPaymentRequestDetails();
        accounts.forEach(account -> {
            accountRepository.findByIdAccount(account.getDestinationId()).orElseThrow(() -> new DomainException(422, "La cuenta Destino para el Pago no existe"));
        });
        return validators.stream()
                .filter(validator -> !validator.test(paymentRequest))
                .map(ValidateRequestPaymentToMultiple::getErrorMessage)
                .findFirst();
    }
}
