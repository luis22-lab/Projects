package com.example.payments.api;


import com.example.payments.api.dto.PaymentDto;
import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.domain.usecase.CreatePaymentUseCase;
import com.example.payments.domain.usecase.DeletePaymentUseCase;
import com.example.payments.domain.usecase.GetPaymentUseCase;
import com.example.payments.domain.usecase.UpdatePaymentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/paymentsMong")
@RequiredArgsConstructor

public class PaymentRestController {

    private final CreatePaymentUseCase createPaymentUseCase;
    private final GetPaymentUseCase getPaymentUseCase;
    private final UpdatePaymentUseCase updatePaymentUseCase;
    private final DeletePaymentUseCase deletePaymentUseCase;
    private final PaymentMapper paymentMapper;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto payment) {
        return ResponseEntity.ok(paymentMapper.mapToPaymentDto(createPaymentUseCase.createPayment(payment)));
    }
    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable String id) {
        return ResponseEntity.ok(paymentMapper.mapToPaymentDto(getPaymentUseCase.getPayment(id)));
    }

    @PutMapping("{id}")
    public ResponseEntity<PaymentDto> updatePayment(@PathVariable String id, @RequestBody PaymentDto payment) {
        return ResponseEntity.ok(paymentMapper.mapToPaymentDto(updatePaymentUseCase.updatePayment(id,payment)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaymentById(@PathVariable String id) {
        Boolean deleted = deletePaymentUseCase.deletePayment(id);
        return deleted ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
