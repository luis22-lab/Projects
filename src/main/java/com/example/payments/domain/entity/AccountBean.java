package com.example.payments.domain.entity;

import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.enums.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor

public class AccountBean implements Account{

    private final Long id;
    private BigDecimal initialBalance;
    private final String name;
    private final PaymentMethod paymentMethod;
    private final Boolean authorized;
    private final AccountStatusEnum AccountStatus;

}
