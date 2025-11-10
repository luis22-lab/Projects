package com.example.payments.api;

import com.example.payments.api.dto.PaymentRequestDto;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.application.mapper.PaymentRequestMapper;
import com.example.payments.domain.usecase.CreatePaymentRequestUseCase;
import com.example.payments.domain.usecase.FindPaymentRequestUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentRequests")
@RequiredArgsConstructor
@Slf4j
public class PaymentRequestRestController {

    private final PaymentRequestMapper map;
    private final CreatePaymentRequestUseCase createPaymentRequestUseCase;
    private final FindPaymentRequestUseCase findPaymentRequestUseCase;

    @PostMapping
    public ResponseEntity<PaymentRequestDto> postPaymentRequest(@RequestBody PaymentRequestDto paymentRequestDto) {
            return ResponseEntity.ok(new PaymentRequestDto(createPaymentRequestUseCase.apply(map.mapPaymentRequestToBean(paymentRequestDto))));
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<PaymentRequestDto> getPaymentRequest(@PathVariable String requestId) {
        return ResponseEntity.ok(new PaymentRequestDto(findPaymentRequestUseCase.apply(requestId)));
    }
}
