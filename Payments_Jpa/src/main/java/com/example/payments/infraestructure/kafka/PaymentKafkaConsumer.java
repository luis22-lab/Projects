package com.example.payments.infraestructure.kafka;

import com.example.payments.api.dto.PaymentCreatedEvent;
import com.example.payments.infraestructure.entity.EventEntity;
import com.example.payments.infraestructure.repository.EventProcessedJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentKafkaConsumer {

    private final EventProcessedJpaRepository eventProcessedJpaRepository;

    @KafkaListener(topics = "payments.created",groupId = "payment_group",containerFactory = "paymentKafkaListenerContainerFactory")
    @TransactionalEventListener(
            phase = TransactionPhase.AFTER_COMMIT
    )
    public void consume(PaymentCreatedEvent event){

        if(eventProcessedJpaRepository.existsById(event.eventId())){
            return;
        }
        log.info("******** Received Payment from topic {}",event);
        eventProcessedJpaRepository.save(new EventEntity(event.eventId(), Instant.now()));
    }
}
