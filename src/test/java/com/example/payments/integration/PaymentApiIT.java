package com.example.payments.integration;

import com.example.payments.api.dto.PaymentDto;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.AccountBean;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.example.payments.infraestructure.mapper.AccountEntityMapper;
import com.example.payments.infraestructure.repository.AccountJpaRepository;
import com.example.payments.infraestructure.repository.PaymentJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)/*Levanta un servidor embebido llamadas hhtp(restTemplate..)*/
@ActiveProfiles("test")
@Slf4j
class PaymentApiIT {

    @Autowired
    private AccountJpaRepository accountRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PaymentJpaRepository paymentRepository;

    @BeforeEach
    void setup() {
        paymentRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    void tesPostPayment() {


        final var accountOrigen = AccountBean.builder()
                .idAccount(12345L)
                .name("CAIXA")
                .paymentMethod(PaymentMethod.CHECK)
                .initialBalance(BigDecimal.valueOf(1))
                .accountStatus(AccountStatusEnum.ACTIVE)
                .authorized(true)
                .build();

        final var accountDestino = AccountBean.builder()
                .idAccount(123456L)
                .name("ING")
                .paymentMethod(PaymentMethod.CHECK)
                .initialBalance(BigDecimal.valueOf(1))
                .accountStatus(AccountStatusEnum.ACTIVE)
                .authorized(true)
                .build();
        final var payment = PaymentBean.builder()
                .requestId("0000001")
                .amount(BigDecimal.valueOf(100.0))
                .paymentMethod(PaymentMethod.CHECK)
                .status(PaymentStatusEnum.DONE)
                .createDate(LocalDateTime.now())
                .dateValue(LocalDateTime.now())
                .destinationId(123456L)
                .origin(12345L).build();

        accountRepository.save(AccountEntityMapper.INSTANCE.toEntity(accountOrigen));
        accountRepository.save(AccountEntityMapper.INSTANCE.toEntity(accountDestino));

        final var dto = PaymentMapper.INSTANCE.mapBeanToDto(payment);

        final var response = restTemplate.postForEntity("/payments", dto, PaymentDto.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("0000001", response.getBody().requestId());
        assertEquals(BigDecimal.valueOf(100.0), response.getBody().amount());

        final var saved = paymentRepository.findByRequestId("0000001").orElseThrow();
        assertEquals(PaymentStatusEnum.DONE, saved.getStatus());

    }
    @AfterEach
    void cleanup() {
        paymentRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
