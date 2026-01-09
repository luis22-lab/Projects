package com.example.payments.infraestructure.repository;

import com.example.payments.infraestructure.entity.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaymentMongoRepository extends MongoRepository<PaymentEntity, String> {
    Optional<PaymentEntity> findPaymentByRequestId(String paymentRequestId);
}
