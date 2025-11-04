package com.example.payments.domain.validator;

import com.example.payments.domain.entity.Account;
import com.example.payments.domain.entity.Payment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class ValidateAccountStatus implements ValidateAccount {



    @Override
    public boolean test(Account account) {
       // return validateAccountPaymentMethod(payMethod).test(account);
    }

    private Predicate<Account> validateAccountPaymentMethod(Payment payment) {
        //return account -> account.getPaymentMethod().equals(payment.getPaymentMethod());
    }

}
