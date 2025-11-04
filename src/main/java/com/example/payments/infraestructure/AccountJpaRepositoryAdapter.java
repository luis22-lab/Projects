package com.example.payments.infraestructure;

import com.example.payments.domain.entity.Account;
import com.example.payments.domain.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor

public class AccountJpaRepositoryAdapter implements AccountRepository {
    @Override
    public Optional<Account> findByRequestId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Account> findById(String name) {
        return Optional.empty();
    }
}
