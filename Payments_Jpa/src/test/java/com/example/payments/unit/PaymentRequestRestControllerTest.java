package com.example.payments.unit;

import com.example.payments.api.rest.PaymentRequestRestController;
import com.example.payments.api.dto.PaymentRequestDto;
import com.example.payments.application.mapper.PaymentRequestMapper;
import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.domain.usecase.CreatePaymentRequestUseCase;
import com.example.payments.domain.usecase.FindPaymentRequestUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class PaymentRequestRestControllerTest {


    @InjectMocks
    private PaymentRequestRestController controller;

    @Mock private CreatePaymentRequestUseCase createPaymentRequestUseCase;
    @Mock private FindPaymentRequestUseCase findUseCase;
    @Mock private PaymentRequestMapper map ;

    @Test
    void testPostPaymentRequest() {

        PaymentRequestDto dto = Mockito.mock(PaymentRequestDto.class);
        PaymentRequestBean bean = Mockito.mock(PaymentRequestBean.class);

        when(map.mapPaymentRequestToBean(any())).thenReturn(bean);
        when(createPaymentRequestUseCase.apply(any())).thenReturn(bean);
        when(map.mapPaymentRequestToDto(bean)).thenReturn(dto);

        ResponseEntity<PaymentRequestDto> actual = controller.postPaymentRequest(dto);

        verify(createPaymentRequestUseCase).apply(bean);

        assertNotNull(actual);
        assertTrue(actual.hasBody());
        assertEquals(dto, actual.getBody());
        assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    /*
    @Test
    void testGetPaymentRequest() {

        PaymentRequestDto dto = Mockito.mock(PaymentRequestDto.class);
        PaymentRequestBean bean = Mockito.mock(PaymentRequestBean.class);
        final var idRequest= "01B";

        when(map.mapPaymentRequestToBean(any())).thenReturn(bean);
        when(map.mapPaymentRequestToDto(bean)).thenReturn(dto);

        final var actual = controller.getPaymentRequest(idRequest);

        verify(findUseCase).apply(idRequest);
        assertTrue(actual.hasBody());
        assertEquals(dto, actual.getBody());

    }
    */

}
