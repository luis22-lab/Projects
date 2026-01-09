package com.example.grpc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreeterController {
    private final PaymentsGrpcClient paymentsGrpcClient;
    @Autowired
    public GreeterController(PaymentsGrpcClient paymentsGrpcClient) {
        this.paymentsGrpcClient = paymentsGrpcClient;
    }
    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return paymentsGrpcClient.Response(name);
    }

}
