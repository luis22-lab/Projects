package com.example.payments.application.predicate;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.predicate.ValidateRequestPayment;
import com.example.payments.domain.repository.AccountRepository;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class ValidateDestinationStatusImpl implements ValidateRequestPayment {

    private final AccountRepository accountRepository;

    public boolean test(Payment payment) {
        return validateDestinationStatus(payment);
    }

    private Boolean validateDestinationStatus(Payment payment) {
        return accountRepository.findByIdAccount(payment.getDestinationId())
                .map(account -> account.getAccountStatus().equals(AccountStatusEnum.ACTIVATED))
                .orElse(false);
    }

    @Override
    public String getErrorMessage() {
        return "la cuenta destino está bloqueada";
    }
}
