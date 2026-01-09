package com.example.payments.infraestructure.entity;

import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity(name = "account")
@Data

public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable= false)
    private Long id;

    @Column(name = "idAccount",nullable = false, updatable = false)
    private Long idAccount;

    @Column(name = "name",nullable = false, updatable = false)
    private String name;

    @Column(name = "initialBalance",nullable = false, updatable = false)
    BigDecimal initialBalance;

    @Enumerated(EnumType.STRING)
    @Column(name = "paymentMethod",nullable = false, updatable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "authorized",nullable = false, updatable = false)
    private boolean authorized;

    @Enumerated(EnumType.STRING)
    @Column(name = "accountStatus",nullable = false, updatable = false)
    AccountStatusEnum accountStatus;




}

