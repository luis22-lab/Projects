package com.example.payments.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum AccountStatusEnum {
    LOCKED,ACTIVE
}
