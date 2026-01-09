package com.example.payments.infraestructure;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.infraestructure.Mapper.PaymentEntityMapper;
import com.example.payments.infraestructure.repository.PaymentMongoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PaymentMongoRepositoryAdapter implements PaymentRepository {

    private final PaymentMongoRepository paymentMongoRepository;
    private final PaymentEntityMapper mapper;

    @Override
    public Optional<Payment> save(Payment payment) {
        final var f = paymentMongoRepository.save(mapper.mapToEntity(payment));
        final var h = mapper.mapToBean(f);
        return Optional.of(mapper.mapToBean(paymentMongoRepository.save(mapper.mapToEntity(payment))));
    }

    @Override
    public Optional<Payment> updatePaymentById(Payment payment) {
        return Optional.ofNullable(mapper.mapToBean(paymentMongoRepository.save(mapper.mapToEntity(payment))));
    }

    @Override
    public Boolean deletePaymentById(String id) {
        if(paymentMongoRepository.existsById(id)){
            paymentMongoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Optional<Payment> findPaymentById(String id) {
        return paymentMongoRepository.findById(id).map(mapper::mapToBean);
    }

    @Override
    public Optional<Payment> findPaymentByRequestId(String requestId) {
        return paymentMongoRepository.findPaymentByRequestId(requestId).map(mapper::mapToBean);
    }
}
