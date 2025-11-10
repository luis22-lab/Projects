package com.example.payments.api;


import ch.qos.logback.core.net.SyslogOutputStream;
import com.example.payments.api.dto.PaymentDto;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.usecase.CreatePaymentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Validated
@Slf4j
public class PaymentRestController {

    private final CreatePaymentUseCase createPaymentUseCase;
    private final PaymentMapper map;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto request) {
        return ResponseEntity.ok(new PaymentDto(createPaymentUseCase.apply(map.mapPaymentToBean(request))));
    }
}


