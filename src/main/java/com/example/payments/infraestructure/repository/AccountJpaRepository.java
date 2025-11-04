package com.example.payments.infraestructure.repository;

import com.example.payments.infraestructure.entity.AccountEntity;
import com.example.payments.infraestructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountJpaRepository extends JpaRepository<AccountEntity,Long> {
}
