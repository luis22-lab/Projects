package com.example.payments.unit;

import com.example.payments.api.PaymentRestController;
import com.example.payments.api.dto.PaymentDto;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.usecase.CreatePaymentUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentRestControllerTest {


    @InjectMocks
    private PaymentRestController controller;

    @Mock
    private CreatePaymentUseCase useCase;
    @Mock
    private PaymentMapper map;


    @Test
    void testCreatePayment() {

        final var bean = PaymentBean.builder().id(2L).status(PaymentStatusEnum.DONE).amount(BigDecimal.valueOf(2000000)).build();
        final var expected = new PaymentDto(bean);

        Mockito.when(map.mapPaymentToBean(expected)).thenReturn(bean);
        Mockito.when(useCase.apply(bean)).thenReturn(bean);

        final var actual = controller.createPayment(expected);

        verify(useCase).apply(bean);
        assertEquals(expected,actual.getBody());
        assertEquals(HttpStatus.OK,actual.getStatusCode());

    }


}
