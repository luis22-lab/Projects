package com.example.payments.integration;


import com.example.payments.api.dto.PaymentRequestDto;
import com.example.payments.application.mapper.PaymentRequestMapper;
import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.domain.entity.PaymentRequestDetail;
import com.example.payments.domain.entity.PaymentRequestDetailBean;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.example.payments.domain.repository.AccountRepository;
import com.example.payments.domain.repository.PaymentRequestRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("integration")
@Slf4j
public class CreateRequestPaymentMultDIT {

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("payments")
            .withInitScript("db/init.sql");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username",postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password",postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name",postgreSQLContainer::getDriverClassName);
    }

    @Autowired
    private  TestRestTemplate restTemplate;

    @Autowired
    PaymentRequestRepository paymentRequestRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private PaymentRequestMapper map;


    @Test
    void testCreatePaymentMulti(){

        //GIVEN

        final var requestID = "SOLC-"+LocalDateTime.now();
        List<PaymentRequestDetail> list = new ArrayList<>();
        final var detail = PaymentRequestDetailBean
                .builder()
                .requestId(requestID)
                .amount(BigDecimal.valueOf(100.0))
                .paymentMethod(PaymentMethod.TRANSFER)
                .status(PaymentStatusEnum.DONE)
                .createDate(LocalDateTime.now())
                .dateValue(LocalDateTime.now())
                .destinationId(93062249L)
                .origin(93062245L).build();

        list.add(detail);

        final var dto = map.mapPaymentRequestToDto(PaymentRequestBean.builder()
                .requestId(requestID)
                .status(PaymentStatusEnum.DONE)
                .requestDate(LocalDateTime.now())
                .idOrigin(93062245L)
                .paymentRequestDetails(list)
                .build());
        //WHEN
        final var created =  restTemplate.postForObject("/paymentRequests",dto, PaymentRequestDto.class);

        //THEN
        final var saved  = paymentRequestRepository.findByRequestId(dto.getRequestId());

        assertNotNull(saved);
        assertTrue(saved.isPresent());

    }


}
