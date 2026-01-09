package com.example.payments.application.mapper;

import com.example.payments.api.dto.PaymentDto;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.entity.PaymentBean;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)

public interface PaymentMapper {

    PaymentDto mapToPaymentDto(Payment payment);
    PaymentBean mapToPaymentBean(PaymentDto paymentDto);
}
