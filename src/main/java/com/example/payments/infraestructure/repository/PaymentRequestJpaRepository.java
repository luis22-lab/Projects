package com.example.payments.infraestructure.repository;

import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.infraestructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRequestJpaRepository extends JpaRepository<PaymentEntity,Long> {

    Optional<PaymentEntity> findByIdempotencyKey(String idempotencyKey);
}
