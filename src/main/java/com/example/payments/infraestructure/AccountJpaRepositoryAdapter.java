package com.example.payments.infraestructure;

import com.example.payments.application.mapper.AccountMapper;
import com.example.payments.domain.entity.Account;
import com.example.payments.domain.repository.AccountRepository;
import com.example.payments.infraestructure.repository.AccountJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountJpaRepositoryAdapter implements AccountRepository {

    private final AccountJpaRepository accountJpaRepository;
    private final AccountMapper accountMapper;

    @Override
    public Optional<Account> findByIdAccount(Long idAccount) {
        return accountJpaRepository.findByIdAccount(idAccount)
                .map(accountMapper::toBean);
    }

}
