package com.example.payments.infraestructure.mapper;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.infraestructure.entity.PaymentEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentEntityMapper {

    PaymentEntityMapper INSTANCE = Mappers.getMapper(PaymentEntityMapper.class);

    PaymentEntity mapBeanToEntity(Payment payment);

    PaymentBean mapEntityToBean(PaymentEntity paymentEntity);

}