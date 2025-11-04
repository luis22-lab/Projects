package com.example.payments.domain.mapper;

import com.example.payments.api.Dto.PaymentDto;
import com.example.payments.api.Dto.PaymentRequestDto;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.entity.PaymentRequestBean;
import lombok.extern.slf4j.Slf4j;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class PaymentMapperTest {

    //private final EasyRandom easyRandom = new EasyRandom();

    @Test
    public void testMapToPaymentRequestDto(){

        final PaymentMapper mapper = Mockito.mock(PaymentMapper.class);
        final var bean = PaymentRequestBean.builder().id(1L).build();
        final var expectedDto = new PaymentRequestDto(bean);

        when(mapper.mapToPaymentRequestDto(bean)).thenReturn(expectedDto);

        final var actual =  new PaymentRequestDto(mapper.mapToPaymentRequestDto(bean));
        verify(mapper).mapToPaymentRequestDto(bean);
        assertEquals(expectedDto,actual);
        log.info("test 1 execute");
    }


    @Test
    public void testMapToPaymentRequestBean() {

        final PaymentMapper mapper = Mockito.mock(PaymentMapper.class);
        final PaymentRequestBean bean  = PaymentRequestBean.builder().id(1L).build();
        final var expectedDto = new PaymentRequestDto(bean);

        when(mapper.mapToPaymentRequestBean(expectedDto)).thenReturn(bean);

        final PaymentRequestBean actual = mapper.mapToPaymentRequestBean(expectedDto);

        verify(mapper).mapToPaymentRequestBean(expectedDto);
        assertEquals(bean,actual);
        log.info("test 2 execute");

    }

    @Test
    public void testMapToPaymentBean() {

        final PaymentMapper mapper = Mockito.mock(PaymentMapper.class);
        final PaymentBean bean  = PaymentBean.builder().id(2L).build();
        final var expectedDto = new PaymentDto(bean);

        when(mapper.mapToPaymentBean(expectedDto)).thenReturn(bean);

        final PaymentBean actual = mapper.mapToPaymentBean(expectedDto);

        verify(mapper).mapToPaymentBean(expectedDto);
        assertEquals(bean,actual);

        log.info("test 3 execute");
    }
    @Test
    public void testMapToPaymentDto(){

        final PaymentMapper mapper = Mockito.mock(PaymentMapper.class);
        final PaymentBean bean  = PaymentBean.builder().id(1L).build();
        final var expectedDto = new PaymentDto(bean);

        when(mapper.mapToPaymentDto(bean)).thenReturn(expectedDto);

        final var actual =  new PaymentDto(mapper.mapToPaymentDto(bean));
        verify(mapper).mapToPaymentDto(bean);
        assertEquals(expectedDto,actual);

        log.info("test 4 execute");

    }


}
