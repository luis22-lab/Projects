package com.example.payments.domain.repository;

import com.example.payments.domain.entity.Account;
import com.example.payments.domain.entity.Payment;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findByRequestId(Long id);

    Optional<Account> findById(String name);
}
