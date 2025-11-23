package com.example.payments.application.mapper;

import com.example.payments.api.dto.PaymentRequestDto;
import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.domain.entity.PaymentRequestDetailBean;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = PaymentRequestDetailMapper.class)

public interface PaymentRequestMapper {

    @Mapping(target = "paymentRequestDetails",source = "paymentRequestDetails",qualifiedByName = "toDto")
    PaymentRequestDto mapPaymentRequestToDto(PaymentRequest paymentRequest);
    @Mapping(target = "paymentRequestDetails",source = "paymentRequestDetails",qualifiedByName = "toBean")
    PaymentRequestBean mapPaymentRequestToBean(PaymentRequest paymentRequest);

    @Mapping(target = "paymentRequestDetails", source = "paymentRequestDetails")
    PaymentRequestBean mapToBeanRequest_Detail(PaymentRequest request, List<PaymentRequestDetailBean> paymentRequestDetails);
}
