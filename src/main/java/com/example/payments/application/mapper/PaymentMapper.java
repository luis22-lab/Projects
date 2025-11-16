package com.example.payments.application.mapper;

import com.example.payments.api.dto.PaymentDto;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.entity.PaymentBean;
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

    PaymentDto  mapBeanToDto(Payment payment);
    PaymentBean mapDtoToBean(PaymentDto paymentDto);

}
