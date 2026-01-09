package com.example.payments.application.mapper;

import com.example.payments.api.dto.PaymentRequestDetailDto;
import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.entity.PaymentRequestDetail;
import com.example.payments.domain.entity.PaymentRequestDetailBean;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PaymentRequestDetailMapper {

    @Named("toBean")
    PaymentRequestDetailBean mapPaymentRequestDetailToBean(PaymentRequestDetail paymentRequestDetail);
    @Named("toDto")
    PaymentRequestDetailDto mapPaymentRequestDetailToDto(PaymentRequestDetail paymentRequestDetail);

    PaymentBean mapPaymentRequestDetailToPaymentBean(PaymentRequestDetail paymentRequestDetail);

}
