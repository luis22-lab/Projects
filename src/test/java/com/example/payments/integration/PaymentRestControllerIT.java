package com.example.payments.integration;

import com.example.payments.api.dto.PaymentDto;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.usecase.CreatePaymentUseCase;
import com.example.payments.infraestructure.repository.PaymentJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Tag("IntegrationTest")
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class PaymentRestControllerIT {

    private  EasyRandom easyRandom;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentJpaRepository paymentJpaRepository;

    @MockitoBean
    private CreatePaymentUseCase createPaymentUseCase;

    @BeforeEach
    void setup() {
        paymentJpaRepository.deleteAll();
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.excludeField(field -> field.getName().equals("id"));
        easyRandom = new EasyRandom(parameters);
    }

    @Test
    void testCreatePayment() throws Exception {

        PaymentBean payment = easyRandom.nextObject(PaymentBean.class);

        PaymentDto dto = PaymentMapper.INSTANCE.mapBeanToDto(payment);

        when(createPaymentUseCase.apply(any(PaymentBean.class))).thenReturn(dto);

        String newPago = objectMapper.writeValueAsString(payment);
        log.info("JSON enviado: " + newPago);

        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPago))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(dto.getId()))
                        .andExpect(jsonPath("$.amount").value(dto.getAmount()));
    }

    @AfterEach
    void cleanup() {
        paymentJpaRepository.deleteAll();
    }

}
