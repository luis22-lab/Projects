package com.example.payments.unit;

import com.example.payments.api.PaymentRequestRestController;
import com.example.payments.api.dto.PaymentRequestDto;
import com.example.payments.application.mapper.PaymentRequestMapper;
import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.example.payments.domain.usecase.CreatePaymentRequestUseCase;
import com.example.payments.domain.usecase.FindPaymentRequestUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class PaymentRequestEntityControllerTest {


    @InjectMocks
    private PaymentRequestRestController controller;

    @Mock private CreatePaymentRequestUseCase createUseCase;
    @Mock private FindPaymentRequestUseCase findUseCase;
    @Mock private PaymentRequestMapper map ;


    @Test
    void testPostPaymentRequest() {

        final var bean = PaymentRequestBean.builder().id(1L).idempotencyKey("01A").status(PaymentStatusEnum.DONE).build();
        final var request = new PaymentRequestDto(bean);

        Mockito.when(map.mapPaymentRequestToBean(request)).thenReturn(bean);
        Mockito.when(createUseCase.apply(bean)).thenReturn(bean);

        final var actual = controller.postPaymentRequest(request);

        verify(createUseCase).apply(bean);
        assertTrue(actual.hasBody());
        assertEquals(request, actual.getBody());

    }

    @Test
    void testGetPaymentRequest() {

        final var idRequest= "01B";
        final var bean = PaymentRequestBean.builder().id(2L).idempotencyKey("01B").status(PaymentStatusEnum.DONE).build();
        final var request = new PaymentRequestDto(bean);

        Mockito.when(findUseCase.apply(idRequest)).thenReturn(bean);
        final var actual = controller.getPaymentRequest(idRequest);

        verify(findUseCase).apply(idRequest);
        assertTrue(actual.hasBody());
        assertEquals(request, actual.getBody());

    }





}
