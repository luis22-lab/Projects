package com.example.payments.unit.domain.mapper;

import com.example.payments.api.dto.PaymentDto;
import com.example.payments.api.dto.PaymentRequestDto;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.application.mapper.PaymentRequestMapper;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.entity.PaymentRequestBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Slf4j
public class PaymentMapperTest {


    @Test
    public void testMapToPaymentRequestDto(){

        final PaymentRequestMapper mapper = Mockito.mock(PaymentRequestMapper.class);
        final var bean = PaymentRequestBean.builder().id(1L).build();
        final var expectedDto = new PaymentRequestDto(bean);

        when(mapper.mapPaymentRequestToDto(bean)).thenReturn(expectedDto);

        final var actual =  new PaymentRequestDto(mapper.mapPaymentRequestToDto(bean));
        verify(mapper).mapPaymentRequestToDto(bean);
        assertEquals(expectedDto,actual);
        log.info("test 1 execute");
    }


    @Test
    public void testMapToPaymentRequestBean() {

        final PaymentRequestMapper mapper = Mockito.mock(PaymentRequestMapper.class);
        final PaymentRequestBean bean  = PaymentRequestBean.builder().id(1L).build();
        final var expectedDto = new PaymentRequestDto(bean);

        when(mapper.mapPaymentRequestToBean(expectedDto)).thenReturn(bean);

        final PaymentRequestBean actual = mapper.mapPaymentRequestToBean(expectedDto);

        verify(mapper).mapPaymentRequestToBean(expectedDto);
        assertEquals(bean,actual);
        log.info("test 2 execute");

    }

    @Test
    public void testMapToPaymentBean() {

        final PaymentMapper mapper = Mockito.mock(PaymentMapper.class);
        final PaymentBean bean  = PaymentBean.builder().id(2L).build();
        final var expectedDto = mapper.mapBeanToDto(bean);

        when(mapper.mapDtoToBean(expectedDto)).thenReturn(bean);

        final PaymentBean actual = mapper.mapDtoToBean(expectedDto);

        verify(mapper).mapDtoToBean(expectedDto);
        assertEquals(bean,actual);

        log.info("test 3 execute");
    }
    @Test
    public void testMapToPaymentDto(){

        final PaymentMapper mapper = Mockito.mock(PaymentMapper.class);
        final PaymentBean bean = PaymentBean.builder().id(1L).build();

        final PaymentDto expectedDto = Mockito.mock(PaymentDto.class);

        when(mapper.mapBeanToDto(bean)).thenReturn(expectedDto);

        final var actual = mapper.mapBeanToDto(bean);

        verify(mapper).mapBeanToDto(bean);
        assertNotNull(actual);

        log.info("test 4 execute");

    }


}
