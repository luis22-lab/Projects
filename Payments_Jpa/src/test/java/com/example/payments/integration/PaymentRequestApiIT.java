package com.example.payments.integration;

import com.example.payments.api.dto.PaymentRequestDetailDto;
import com.example.payments.api.dto.PaymentRequestDto;
import com.example.payments.application.mapper.PaymentRequestMapper;
import com.example.payments.domain.entity.AccountBean;
import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.domain.entity.PaymentRequestDetail;
import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.example.payments.infraestructure.mapper.AccountEntityMapper;
import com.example.payments.infraestructure.repository.AccountJpaRepository;
import com.example.payments.infraestructure.repository.PaymentJpaRepository;
import com.example.payments.infraestructure.repository.PaymentRequestJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@ActiveProfiles("testIt")
@Slf4j
public class PaymentRequestApiIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PaymentRequestJpaRepository paymentRequestJpaRepository;

    @Autowired
    private PaymentJpaRepository paymentJpaRepository;

    @Autowired
    private AccountJpaRepository accountRepository;

    @Autowired
    PaymentRequestMapper map;


    @BeforeEach
    void setup() {
        paymentRequestJpaRepository.deleteAll();
        accountRepository.deleteAll();
        paymentJpaRepository.deleteAll();
    }

    @Test
    void testPostRequestPayment() {

        List<PaymentRequestDetail> listPayments = new ArrayList<>();

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
        PaymentRequestDetailDto paymentDetail = new PaymentRequestDetailDto(null,"SOLC-MULT-001",12345L,123456L,PaymentMethod.CHECK,LocalDateTime.now(),LocalDateTime.now(),BigDecimal.TEN,PaymentStatusEnum.PENDING);
        listPayments.add(paymentDetail);

        final var paymentRequest = PaymentRequestBean.builder()
                .requestId("SOLC-MULT-001")
                .status(PaymentStatusEnum.PENDING)
                .requestDate(LocalDateTime.now())
                .idOrigin(12345L)
                .paymentRequestDetails(listPayments)
                .build();

        accountRepository.save(AccountEntityMapper.INSTANCE.toEntity(accountOrigin));
        accountRepository.save(AccountEntityMapper.INSTANCE.toEntity(accountDestiny));

        final var dto = map.mapPaymentRequestToDto(paymentRequest);

        final var response = restTemplate.postForEntity("/paymentRequests", dto, PaymentRequestDto.class);

        final var saved  = paymentRequestJpaRepository.findById(response.getBody().getId());

        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getPaymentRequestDetails());
        assertNotNull(saved);


    }

    @AfterEach
    void cleanUp() {
        paymentRequestJpaRepository.deleteAll();
        accountRepository.deleteAll();
        paymentJpaRepository.deleteAll();
    }
}
