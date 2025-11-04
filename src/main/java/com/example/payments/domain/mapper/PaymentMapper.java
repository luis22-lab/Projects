package com.example.payments.domain.mapper;

import com.example.payments.api.Dto.PaymentDto;
import com.example.payments.api.Dto.PaymentRequestDto;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.entity.PaymentRequestBean;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentMapper {

    PaymentRequestDto mapToPaymentRequestDto(PaymentRequestBean paymentRequestBean);
    PaymentRequestBean mapToPaymentRequestBean(PaymentRequestDto paymentRequestDto);

    PaymentDto mapToPaymentDto(PaymentBean paymentBean);
    PaymentBean mapToPaymentBean(PaymentDto paymentDto);


}
