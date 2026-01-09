package com.example.payments.integration;


import com.example.payments.api.dto.PaymentDto;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.example.payments.domain.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testcontainers.shaded.org.awaitility.Awaitility.await;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@ActiveProfiles("integration")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
public class CreatePaymentDIT {

    @Container
    static KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.5.0"));

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
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private  TestRestTemplate restTemplate;

    @Autowired
    private PaymentRepository   paymentRepository;

    @Autowired
    private PaymentMapper map;

    @Test
    void testCreatePayment(){
        final var dto = map.mapBeanToDto(PaymentBean.builder()
                .requestId("SOLC-"+LocalDateTime.now())
                .amount(BigDecimal.valueOf(100.0))
                .paymentMethod(PaymentMethod.TRANSFER)
                .status(PaymentStatusEnum.DONE)
                .createDate(LocalDateTime.now())
                .dateValue(LocalDateTime.now())
                .destinationId(93062249L)
                .origin(93062245L).build());

        final var created =  restTemplate.postForObject("/payments",dto, PaymentDto.class);
        final var saved  = paymentRepository.findOneByRequestId(dto.getRequestId());

        assertTrue(saved.isPresent());
        assertEquals(saved.get().getPaymentMethod(),dto.getPaymentMethod());
        log.info("********** testCreatePayment  ********** : {}",saved.toString());

    }


}
