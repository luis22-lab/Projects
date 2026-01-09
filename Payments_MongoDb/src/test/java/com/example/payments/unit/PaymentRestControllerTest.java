package com.example.payments.unit;

import com.example.payments.api.PaymentRestController;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentRestControllerTest {

    @InjectMocks
    private PaymentRestController paymentRestController;

    @Mock
    private CreatePaymentUseCase createPaymentUseCase;

    @Mock
    private PaymentMapper map;

    @Test
    void test_paymentRestController_Post() {

        final var bean = Mockito.mock(PaymentBean.class);
        final var dto = Mockito.mock(PaymentDto.class);

        when(createPaymentUseCase.createPayment(any())).thenReturn(bean);
        when(map.mapToPaymentDto(any())).thenReturn(dto);

        final var result = paymentRestController.createPayment(dto);

        verify(createPaymentUseCase).createPayment(dto);
        verify(map).mapToPaymentDto(bean);
        assertEquals(dto,result.getBody());
        assertEquals(HttpStatus.OK, result.getStatusCode());
        verifyNoMoreInteractions(createPaymentUseCase);

    }

}
