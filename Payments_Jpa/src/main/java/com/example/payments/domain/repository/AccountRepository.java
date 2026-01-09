package com.example.payments.domain.repository;

import com.example.payments.domain.entity.Account;

import java.util.Optional;

public interface AccountRepository {

    Optional<Account> findByIdAccount(Long id);

}
