package com.example.payments.unit;

import com.example.payments.api.rest.PaymentRestController;
import com.example.payments.api.dto.PaymentDto;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.usecase.CreatePaymentUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

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

        final var bean = Mockito.mock(PaymentBean.class);
        final var dto= Mockito.mock(PaymentDto.class);

        Mockito.when(map.mapDtoToBean(dto)).thenReturn(bean);
        Mockito.when(map.mapBeanToDto(bean)).thenReturn(dto);
        Mockito.when(useCase.apply(bean)).thenReturn(bean);

        final var actual = controller.createPayment(dto);

        verify(useCase).apply(bean);
        assertEquals(dto,actual.getBody());
        assertEquals(HttpStatus.OK,actual.getStatusCode());

    }


}
