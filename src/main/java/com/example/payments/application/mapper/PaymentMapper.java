package com.example.payments.application.mapper;

import com.example.payments.api.dto.PaymentDto;
import com.example.payments.api.dto.PaymentRequestDto;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.infraestructure.entity.PaymentEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    PaymentDto  mapPaymentToDto(PaymentBean paymentBean);
    PaymentBean mapPaymentToBean(PaymentDto paymentDto);

    PaymentEntity mapPaymentToEntity(PaymentBean paymentBean);
    PaymentBean mapPaymentToBean(PaymentEntity paymentEntity);

}
