package com.example.payments.infraestructure.repository;

import com.example.payments.infraestructure.entity.PaymentRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRequestJpaRepository extends JpaRepository<PaymentRequestEntity,Long> {

    Optional<PaymentRequestEntity> findByRequestId(String requestId);
}
