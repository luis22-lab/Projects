package com.example.payments.application.event;


import com.example.payments.domain.entity.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePaymentEvent {

    private final Payment payment;
}
