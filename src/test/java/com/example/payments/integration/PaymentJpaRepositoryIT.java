package com.example.payments.integration;


import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.infraestructure.mapper.PaymentEntityMapper;
import com.example.payments.infraestructure.repository.PaymentJpaRepository;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.jeasy.random.FieldPredicates.named;

@DataJpaTest
@Transactional
public class PaymentJpaRepositoryIT {

    private  EasyRandom easyRandom;

    @Autowired
    private PaymentJpaRepository repository;

    @Test
    void TestSavePayment() {

        //given
        final var parameters = new EasyRandomParameters()
                .collectionSizeRange(1, 3)
                .randomizationDepth(2)
                .ignoreRandomizationErrors(true)
                .stringLengthRange(5, 15)
                .excludeField(named("id"));

        this.easyRandom = new EasyRandom(parameters);
        final var payment = easyRandom.nextObject(PaymentBean.class);

        //when
        final var saved = repository.save(PaymentEntityMapper.INSTANCE.mapBeanToEntity(payment));

        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getStatus()).isEqualTo(payment.getStatus());

    }

    @AfterEach
    void deleteAll() {
        repository.deleteAll();
    }

}
