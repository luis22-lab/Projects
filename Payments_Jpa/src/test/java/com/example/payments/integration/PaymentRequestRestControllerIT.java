package com.example.payments.integration;

import com.example.payments.api.dto.PaymentRequestDetailDto;
import com.example.payments.application.mapper.PaymentRequestMapper;
import com.example.payments.domain.entity.AccountBean;
import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.domain.entity.PaymentRequestDetail;
import com.example.payments.domain.enums.AccountStatusEnum;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.example.payments.infraestructure.mapper.AccountEntityMapper;
import com.example.payments.infraestructure.repository.AccountJpaRepository;
import com.example.payments.infraestructure.repository.PaymentRequestJpaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc //Se usa en pruebas de Spring Boot para configurar automáticamente un MockMvc sin necesidad de levantar un servidor completo.
@ActiveProfiles("testIt")
@Slf4j
public class PaymentRequestRestControllerIT {

    private final static String path = "/paymentRequests";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentRequestJpaRepository paymentRequestJpaRepository;

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    @Autowired
    private PaymentRequestMapper paymentRequestMapper;


    @BeforeEach
    void setUp(){
        paymentRequestJpaRepository.deleteAll();
        accountJpaRepository.deleteAll();
    }

    @Test
    void testPaymentRequestController() throws Exception {


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

        PaymentRequestDetailDto paymentDetail = new PaymentRequestDetailDto(null,"SOLC-MULT-011",12345L,123456L,PaymentMethod.CHECK,LocalDateTime.now(),LocalDateTime.now(),BigDecimal.TEN,PaymentStatusEnum.PENDING);
        listPayments.add(paymentDetail);

        final var paymentRequest = PaymentRequestBean.builder()
                .requestId("SOLC-MULT-011")
                .status(PaymentStatusEnum.PENDING)
                .requestDate(LocalDateTime.now())
                .idOrigin(12345L)
                .paymentRequestDetails(listPayments)
                .build();

        accountJpaRepository.save(AccountEntityMapper.INSTANCE.toEntity(accountOrigin));
        accountJpaRepository.save(AccountEntityMapper.INSTANCE.toEntity(accountDestiny));

        final var paymentRequestDto = paymentRequestMapper.mapPaymentRequestToDto(paymentRequest);

        log.info("+-+-++-++-+-+-+-+- testPaymentRequestController, requestDto sent: {}", paymentRequestDto);
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(paymentRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());

    }

    @AfterEach
    void setDown(){
        paymentRequestJpaRepository.deleteAll();
        accountJpaRepository.deleteAll();
    }
}
