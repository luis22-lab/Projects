package com.example.payments.infraestructure.entity;

import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "account")
@Data

public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable= false)
    private Long id;

    @Column(name = "name",nullable = false, updatable = false)
    private String name;

    @Column(name = "initialBalance",nullable = false, updatable = false)
    BigDecimal initialBalance;

    @Column(name = "paymentMethod",nullable = false, updatable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "authorized",nullable = false, updatable = false)
    private boolean authorized;

    @Column(name = "status",nullable = false, updatable = false)
    PaymentStatusEnum status;




}

