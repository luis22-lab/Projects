package com.example.payments.api;


import com.example.payments.api.Dto.PaymentDto;
import com.example.payments.domain.mapper.PaymentMapper;
import com.example.payments.domain.usecase.CreatePaymentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/payments")
@RequiredArgsConstructor
@Validated
@Slf4j
public class PaymentRestController {

    private final CreatePaymentUseCase createPaymentUseCase;
    private final PaymentMapper map;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@Valid @RequestBody PaymentDto request) {
        return ResponseEntity.ok(new PaymentDto(createPaymentUseCase.apply(map.mapToPaymentBean(request))));
    }


}
