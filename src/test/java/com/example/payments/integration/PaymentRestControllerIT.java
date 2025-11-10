package com.example.payments.integration;

import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.infraestructure.repository.PaymentJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Tag("IntegrationTest")
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class PaymentRestControllerIT {

    private final EasyRandom easyRandom = new EasyRandom();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentJpaRepository paymentJpaRepository;

    @Test
    void testCreatePayment() throws Exception {

        paymentJpaRepository.deleteAll();

        PaymentBean payment = easyRandom.nextObject(PaymentBean.class);

        String newPago = objectMapper.writeValueAsString(payment);
        log.info("JSON enviado: " + newPago);

        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPago));

        assert(paymentJpaRepository.findAll().size() == 1);
    }

}
