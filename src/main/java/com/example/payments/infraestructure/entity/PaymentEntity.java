package com.example.payments.infraestructure.entity;

import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "payment")
@Data

public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable= false)
    private Long id;
    @Column(name = "requestId", nullable = false, updatable = false)
    private String requestId;
    @Column(name = "origin",nullable = false, updatable = false)
    private String origin;
    @Column(name = "destination",nullable = false, updatable = false)
    String destination;
    @Column(name = "paymentMethod",nullable = false, updatable = false)
    private PaymentMethod paymentMethod;
    @Column(name = "createDate",nullable = false, updatable = false)
    private LocalDateTime createDate;
    @Column(name = "dateValue",nullable = false, updatable = false)
    LocalDateTime dateValue;
    @Column(name = "amount",nullable = false, updatable = false)
    private BigDecimal amount;
    @Column(name = "status",nullable = false, updatable = false)
    PaymentStatusEnum status;



}
