package com.example.payments.integration;

import com.example.payments.api.dto.PaymentRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("integration")
@Slf4j
public class FindRequestPaymentDIT {

    @LocalServerPort
    private int port;

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
    private TestRestTemplate restTemplate;


    @Test
    void testGetByRequestId() {

        String url =  "http://localhost:" + port + "/paymentRequests/SOLC-005";

        final var response = restTemplate.getForObject(url, PaymentRequestDto.class);

        assertNotNull(response);
        assertEquals("SOLC-005",response.getRequestId());
        assertNotNull(response.getPaymentRequestDetails());

        log.info("********** testFindByRequestId *********** {}",response.toString());

    }

}
