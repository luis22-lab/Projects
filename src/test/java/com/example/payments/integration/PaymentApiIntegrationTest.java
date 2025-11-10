package com.example.payments.integration;

import com.example.payments.api.dto.PaymentDto;
import com.example.payments.application.mapper.AccountMapper;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.AccountBean;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("test")
@Slf4j
class PaymentApiIntegrationTest {

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
    void testGetPaymentByRequestId() {


        final var accountOrigen = AccountBean.builder()
                .idAccount(12345L)
                .name("CAIXA")
                .paymentMethod(PaymentMethod.CHEQUE)
                .initialBalance(BigDecimal.valueOf(1))
                .accountStatus(AccountStatusEnum.ACTIVATED)
                .authorized(true)
                .build();

        final var accountDestino = AccountBean.builder()
                .idAccount(123456L)
                .name("ING")
                .paymentMethod(PaymentMethod.CHEQUE)
                .initialBalance(BigDecimal.valueOf(1))
                .accountStatus(AccountStatusEnum.ACTIVATED)
                .authorized(true)
                .build();
        final var payment = PaymentBean.builder()
                .requestId("0000001")
                .amount(BigDecimal.valueOf(100.0))
                .paymentMethod(PaymentMethod.CHEQUE)
                .status(PaymentStatusEnum.DONE)
                .createDate(LocalDateTime.now())
                .dateValue(LocalDateTime.now())
                .destinationId(123456L)
                .origin(12345L).build();

        accountRepository.save(AccountMapper.INSTANCE.toEntity(accountOrigen));
        accountRepository.save(AccountMapper.INSTANCE.toEntity(accountDestino));

        final var dto = PaymentMapper.INSTANCE.mapPaymentToDto(payment);

        ResponseEntity<PaymentDto> response = restTemplate.postForEntity("/payments", dto, PaymentDto.class);
        log.info("/*/**/*/*//*/*/*/**/*/*/*/*/*/*/*/*/*/*/*/*/"+response.getBody().toString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("0000001", response.getBody().getRequestId());
        assertEquals(BigDecimal.valueOf(100.0), response.getBody().getAmount());

        final var saved = paymentRepository.findByRequestId("0000001").orElseThrow();
        assertEquals(PaymentStatusEnum.DONE, saved.getStatus());

    }
    @AfterEach
    void cleanup() {
        paymentRepository.deleteAll();
        accountRepository.deleteAll();
    }
}
