package com.example.payments.infraestructure.kafka;

import com.example.payments.api.dto.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentKafkaProducer {

    private final KafkaTemplate<String, PaymentCreatedEvent> kafkaTemplate;
    private static final String PAYMENT_TOPIC = "payments.created";

    @TransactionalEventListener(
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void sendEventPayment(PaymentCreatedEvent payment){
        kafkaTemplate.send(PAYMENT_TOPIC,payment.paymentId(),payment);
        log.info("*-******-*- EVENT SENT : {}",payment);
    }
}
