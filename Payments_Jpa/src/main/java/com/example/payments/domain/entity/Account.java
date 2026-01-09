package com.example.payments.domain.entity;

import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.enums.PaymentMethod;

import java.math.BigDecimal;

public interface Account {

    Long getId();
    Long getIdAccount();
    String getName();
    BigDecimal getInitialBalance();
    PaymentMethod getPaymentMethod();
    Boolean getAuthorized();
    AccountStatusEnum getAccountStatus();

}