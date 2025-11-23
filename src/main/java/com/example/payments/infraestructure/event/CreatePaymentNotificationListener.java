package com.example.payments.infraestructure.event;

import com.example.payments.application.event.CreatePaymentEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreatePaymentNotificationListener {

    @EventListener
    public void handleCreatePaymentEvent(CreatePaymentEvent event) {
        log.info("*_*_*_*_*_*_*_*_*_* --> Internal Event: CreatePaymentEvent");
    }
}
