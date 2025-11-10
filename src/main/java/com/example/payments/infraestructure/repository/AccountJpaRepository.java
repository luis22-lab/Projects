package com.example.payments.infraestructure.repository;

import com.example.payments.infraestructure.entity.AccountEntity;
import com.example.payments.infraestructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountJpaRepository extends JpaRepository<AccountEntity,Long> {
    Optional<AccountEntity> findByIdAccount(Long idAccount);
}
