package com.example.payments.domain.validator;

import com.example.payments.domain.entity.Account;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
@AllArgsConstructor
public class ValidateAccountAuthorizedImpl implements ValidateAccount {

    @Override
    public boolean test(Account account) {
        return validateAccountAuthorized(account).test(account);
    }
    private  Predicate<Account> validateAccountAuthorized(Account account) {
        return Account::getAuthorized;
    }

}

