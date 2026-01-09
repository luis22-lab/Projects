package com.example.payments.integration;

import com.example.payments.domain.entity.AccountBean;
import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.infraestructure.mapper.AccountEntityMapper;
import com.example.payments.infraestructure.repository.AccountJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
@Testcontainers
@ActiveProfiles("integration")
@Slf4j
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CreateAccountDIT {

    @Container
    static PostgreSQLContainer <?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:15")
            .withDatabaseName("payments")
            .withInitScript("db/init.sql");

    @DynamicPropertySource
    static void configureDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.datasource.driver-class-name",() -> "org.postgresql.Driver");
    }

    @Autowired
    AccountJpaRepository accountRepository;

    @Autowired
    AccountEntityMapper map;

    @Test
    void testSaveNewAccount(){
        AccountBean acc = AccountBean.builder()
                .name("BANCO POPULAR")
                .idAccount(93062251L)
                .paymentMethod(PaymentMethod.TRANSFER)
                .accountStatus(AccountStatusEnum.ACTIVE)
                .initialBalance(BigDecimal.TEN)
                .authorized(true).build();
        accountRepository.save(map.toEntity(acc));
        final var  saved = accountRepository.findByIdAccount(93062251L);
        assertEquals(7,accountRepository.count());
        assertNotNull(saved);
        log.info("********** testSaveNewAccount ********** : {}",saved.toString());
    }
}

