package com.example.grpc;


import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class PaymentGrpcService extends  PaymentServiceGrpc.PaymentServiceImplBase {
    @Override
    public void make(PaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        String payment = request.getId();
        String message = String.format("Probando grpc, %s!", payment);
        PaymentResponse resp  = PaymentResponse.newBuilder().setMessage(message).build();
        responseObserver.onNext(resp);
        responseObserver.onCompleted();
    }
}
