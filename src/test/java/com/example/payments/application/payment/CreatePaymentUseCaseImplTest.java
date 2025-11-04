package com.example.payments.application.payment;

import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.repository.PaymentRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class CreatePaymentUseCaseImplTest {

    private final EasyRandom easyRandom = new EasyRandom();
    @InjectMocks
    private  CreatePaymentUseCaseImpl createPaymentUseCaseImpl;
    @Mock
    private PaymentRepository repository;

    public void  testCreatePaymentUseCase(){

            Payment request = easyRandom.nextObject(Payment.class);
            Optional<Payment> expected = repository.save(request);

            when(createPaymentUseCaseImpl.apply(request)).thenReturn(request);
            when(repository.save(request)).thenReturn(expected);

            final var actual = createPaymentUseCaseImpl.apply(request);

            verify(repository).save(request);

            assertEquals(request,actual);
    }
}
