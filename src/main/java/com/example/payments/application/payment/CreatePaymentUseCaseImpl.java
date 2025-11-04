package com.example.payments.application.payment;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.repository.AccountRepository;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.domain.usecase.CreatePaymentUseCase;
import com.example.payments.domain.validator.ValidateAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreatePaymentUseCaseImpl implements CreatePaymentUseCase {

    public static final int status_422 = 422;
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;
    private final ValidateAccount validateAccount;

    @Override
    public Payment apply(Payment payment) {
        final var exists = paymentRepository.findByRequestId(payment.getRequestId());
        exists.ifPresent(account -> {throw new DomainException(status_422,"Existing payment");});
        final var acc = accountRepository.findById(payment.getRequestId()).orElseThrow(() -> new DomainException(status_422,"Account not found"));
       // validateAccount(acc,payment);

        return paymentRepository.save(payment).orElseThrow(() -> new DomainException(status_422,"Error to save payment"));

    }

   /* public  void validateAccount(Account account,Payment payment) {
        List<Map.Entry<Predicate<Account>, String>> rules = List.of(
                Map.entry(a ->validateAccount.validateAuthorized().test(a), "Account not authorized"),
                Map.entry(a -> validateAccount.validatePaymentMethodAccount(payment).test(a), "Payment method does not supported"),
                Map.entry(a -> validateAccount.validateStatus(payment).test(a), "Account Locked"),
                Map.entry(a -> validateAccount.validateInitialBalance(payment).test(a), "Insufficient balance")
        );
        rules.stream()
                .filter(entry -> !entry.getKey().test(account))
                .findFirst()
                .ifPresent(entry -> { throw new DomainException(status_422,entry.getValue());});
    }*/
}


