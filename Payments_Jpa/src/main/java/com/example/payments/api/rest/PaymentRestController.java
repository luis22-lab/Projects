package com.example.payments.api.rest;


import com.example.payments.api.dto.PaymentDto;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.usecase.CreatePaymentUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(map.mapBeanToDto(createPaymentUseCase.apply(map.mapDtoToBean(request))));
    }

    /*@GetMapping("{id}")
    public ResponseEntity<PaymentDto> getPayment(@PathVariable Long id) {

    }*/
}


