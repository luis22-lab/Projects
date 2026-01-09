package com.example.payments.api.grpc;

import com.example.payments.GetPaymentResponse;
import com.example.payments.PostPaymentRequest;
import com.example.payments.PostPaymentResponse;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.entity.PaymentBean;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperGrpc {

    PaymentBean  protoToBean(PostPaymentRequest paymentRequest);
    PostPaymentResponse beanToProtoResponse(Payment payment);
    GetPaymentResponse beanToGetResponse(Payment payment);
}
