package com.example.payments.integracion;

import com.example.payments.api.dto.PaymentDto;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.enums.PaymentMethodEnum;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.example.payments.infraestructure.repository.PaymentMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration" }
        )
@Testcontainers
@Slf4j
public class PaymentRestControllerMongoDIT {

    @LocalServerPort
    private int port;

    @Container
    static MongoDBContainer mongoDBContainer =  new MongoDBContainer("mongo:4.0.6");

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PaymentMongoRepository paymentMongoRepository;

    @Autowired
    private PaymentMapper  mapper;


    @Test
    void test_CreatePayment() {

        String url = "http://localhost:" + port + "/paymentsMong";
        final var  dto =  mapper.mapToPaymentDto(PaymentBean.builder()
                .amount(BigDecimal.TEN)
                .dateValue(LocalDateTime.now())
                .dateCreated(LocalDateTime.now())
                .idOrigin(12345L)
                .idDestination(123456L)
                .requestId("232234L")
                .paymentMethod(PaymentMethodEnum.CHECK)
                .paymentStatus(PaymentStatusEnum.PENDING)
                .build());
        final var result = testRestTemplate.postForObject(url,dto,PaymentDto.class);

        final var search = paymentMongoRepository.findById(result.id());

        log.info("**********  test_CreatePayment **********: {}",search.toString());

        assertEquals(result.getId(),search.get().getId());
        assertNotNull(result);
    }

   @Test
    void test_GetPaymentById() {

        String url = "http://localhost:" + port + "/paymentsMong/";
        final var  dto =  mapper.mapToPaymentDto(PaymentBean.builder()
                .amount(BigDecimal.TEN)
                .dateValue(LocalDateTime.now())
                .dateCreated(LocalDateTime.now())
                .idOrigin(12345L)
                .idDestination(123456L)
                .requestId("232234L")
                .paymentMethod(PaymentMethodEnum.CHECK)
                .paymentStatus(PaymentStatusEnum.PENDING)
                .build());

        final var saved = testRestTemplate.postForObject(url,dto,PaymentDto.class);
        final var search =  testRestTemplate.getForObject(url+saved.id(),PaymentDto.class);

        log.info("**********  test_GetPaymentById **********: {}",search.toString());

        assertEquals(saved, search);
        assertNotNull(search);
    }

}
