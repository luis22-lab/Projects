package com.example.payments.integration;

import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.AccountBean;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.example.payments.infraestructure.mapper.AccountEntityMapper;
import com.example.payments.infraestructure.repository.AccountJpaRepository;
import com.example.payments.infraestructure.repository.PaymentJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Slf4j
public class PaymentRestControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentJpaRepository paymentJpaRepository;

    @Autowired
    private AccountJpaRepository  accountJpaRepository;

    @Autowired
    private PaymentMapper paymentMapper;


    @BeforeEach
    void setup() {
        paymentJpaRepository.deleteAll();
    }

    @Test
    void testPaymentRestController() throws Exception {

        final var accountOrigin = AccountBean.builder()
                .idAccount(12345L)
                .name("CAIXA")
                .paymentMethod(PaymentMethod.CHECK)
                .initialBalance(BigDecimal.valueOf(1))
                .accountStatus(AccountStatusEnum.ACTIVE)
                .authorized(true)
                .build();

        final var accountDestiny = AccountBean.builder()
                .idAccount(123456L)
                .name("ING")
                .paymentMethod(PaymentMethod.CHECK)
                .initialBalance(BigDecimal.valueOf(1))
                .accountStatus(AccountStatusEnum.ACTIVE)
                .authorized(true)
                .build();


        final var payment  = paymentMapper.mapBeanToDto(PaymentBean.builder()
                .requestId("SOLC-211")
                .status(PaymentStatusEnum.PENDING)
                .destinationId(123456L)
                .origin(12345L)
                .paymentMethod(PaymentMethod.CHECK)
                .amount(BigDecimal.TEN)
                .dateValue(LocalDateTime.now())
                .createDate(LocalDateTime.now())
                .build());

        accountJpaRepository.save(AccountEntityMapper.INSTANCE.toEntity(accountOrigin));
        accountJpaRepository.save(AccountEntityMapper.INSTANCE.toEntity(accountDestiny));

        log.info("*-*-*-*-* PaymentRestControllerIT: JSON SENT: {} " ,payment.toString());

        mockMvc.perform(post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(payment)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.amount").value(payment.getAmount()));

    }


    @AfterEach
    void cleanup() {
        paymentJpaRepository.deleteAll();
    }
}
