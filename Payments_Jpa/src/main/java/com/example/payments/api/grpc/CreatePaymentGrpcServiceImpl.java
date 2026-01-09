package com.example.payments.api.grpc;

import com.example.payments.*;
import com.example.payments.GetPaymentRequest;
import com.example.payments.GetPaymentResponse;
import com.example.payments.PaymentServiceGrpc;
import com.example.payments.PostPaymentRequest;
import com.example.payments.PostPaymentResponse;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.repository.PaymentRepository;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@AllArgsConstructor
@Slf4j
public class CreatePaymentGrpcServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final PaymentRepository paymentRepository;
    private final MapperGrpc mapperGrpc;
    private final PaymentMapper paymentMapper;

    @Override
    public void postPayment(PostPaymentRequest request, StreamObserver<PostPaymentResponse> responseObserver) {

        final var  payment = mapperGrpc.protoToBean(request);
        final var  saved  = paymentRepository.save(payment).orElse(null);
        final var  paymentResponse = mapperGrpc.beanToProtoResponse(saved);
        responseObserver.onNext(paymentResponse);
        responseObserver.onCompleted();
    }
    @Override
    public void getPayment(GetPaymentRequest request, StreamObserver<GetPaymentResponse> responseObserver) {
        final var  saved  = paymentRepository.findById(request.getId()).orElse(null);
        final var paymentResponse = mapperGrpc.beanToGetResponse(saved);
        responseObserver.onNext(paymentResponse);
        responseObserver.onCompleted();
    }
}
