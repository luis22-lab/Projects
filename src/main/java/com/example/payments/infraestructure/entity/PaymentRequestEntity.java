package com.example.payments.infraestructure.entity;

import com.example.payments.domain.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "paymentRequest")
@Data
public class PaymentRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable= false)
    private Long id;

    @Column(name = "requestId",unique= true, nullable = false, updatable = false)
    private String requestId;

    @Column(name = "requestDate",nullable = false, updatable = false)
    LocalDateTime requestDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false, updatable = false)
    PaymentStatusEnum status;
}
