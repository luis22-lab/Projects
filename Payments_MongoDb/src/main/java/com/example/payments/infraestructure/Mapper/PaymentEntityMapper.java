package com.example.payments.infraestructure.Mapper;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.infraestructure.entity.PaymentEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentEntityMapper {

    PaymentEntity mapToEntity(Payment payment);
    PaymentBean mapToBean(PaymentEntity paymentEntity);

}
