package com.example.payments.infraestructure.repository;

import com.example.payments.infraestructure.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<PaymentEntity,Long> {
    Optional<PaymentEntity> findOneByRequestId(String requestId);
    List<PaymentEntity> findByRequestId(String requestId);

}
