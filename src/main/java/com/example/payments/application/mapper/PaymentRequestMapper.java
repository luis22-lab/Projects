package com.example.payments.application.mapper;

import com.example.payments.api.dto.PaymentDto;
import com.example.payments.api.dto.PaymentRequestDto;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.infraestructure.entity.PaymentEntity;
import com.example.payments.infraestructure.entity.PaymentRequestEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentRequestMapper {

   // PaymentRequestMapper INSTANCE = Mappers.getMapper(PaymentRequestMapper.class);

    PaymentRequestDto mapPaymentRequestToDto(PaymentRequestBean paymentRequestBean);
    PaymentRequestBean mapPaymentRequestToBean(PaymentRequestDto paymentRequestDto);

    PaymentRequestEntity mapPaymentRequestBeanToEntity(PaymentRequestBean paymentRequestBean);
    PaymentRequestBean mapPaymentRequestEntityToBean(PaymentRequestEntity paymentRequestEntity);

}
