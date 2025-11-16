package com.example.payments.application.mapper;

import com.example.payments.api.dto.PaymentRequestDto;
import com.example.payments.domain.entity.PaymentRequestBean;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentRequestMapper {

   PaymentRequestMapper INSTANCE = Mappers.getMapper(PaymentRequestMapper.class);

    PaymentRequestDto mapPaymentRequestToDto(PaymentRequestBean paymentRequestBean);
    PaymentRequestBean mapPaymentRequestToBean(PaymentRequestDto paymentRequestDto);

}
