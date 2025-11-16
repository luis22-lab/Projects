package com.example.payments.infraestructure.mapper;

import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.infraestructure.entity.PaymentRequestEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)

public interface PaymentRequestEntityMapper {

    PaymentRequestEntityMapper INSTANCE = Mappers.getMapper(PaymentRequestEntityMapper.class);

    PaymentRequestEntity mapPaymentRequestBeanToEntity(PaymentRequestBean paymentRequestBean);
    PaymentRequestBean mapPaymentRequestEntityToBean(PaymentRequestEntity paymentRequestEntity);
}
