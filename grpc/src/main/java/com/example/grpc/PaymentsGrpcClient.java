package com.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentsGrpcClient {

    private final  PaymentServiceGrpc.PaymentServiceBlockingStub stub;

    public PaymentsGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        this.stub =  PaymentServiceGrpc.newBlockingStub(channel);
    }
    public String Response(String name) {
        PaymentRequest req =PaymentRequest.newBuilder().setAmount(123L).build();
        PaymentResponse resp = stub.make(req);
        return resp.getMessage();
    }
}
