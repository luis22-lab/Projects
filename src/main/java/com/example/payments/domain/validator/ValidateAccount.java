package com.example.payments.domain.validator;

import com.example.payments.domain.entity.Account;
import com.example.payments.domain.entity.Payment;

import java.util.function.Predicate;

public interface ValidateAccount extends Predicate<Account> {

}
