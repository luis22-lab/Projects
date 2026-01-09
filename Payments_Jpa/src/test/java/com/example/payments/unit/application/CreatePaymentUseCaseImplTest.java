package com.example.payments.unit.application;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

public class CreatePaymentUseCaseImplTest {

    /*private final EasyRandom easyRandom = new EasyRandom();
    @InjectMocks
    private CreatePaymentUseCaseImpl createPaymentUseCaseImpl;
    @Mock
    private PaymentRepository repository;

    @Test
    public void  testCreatePaymentUseCase(){
        Payment request = easyRandom.nextObject(PaymentBean.class);

        when(repository.save(request)).thenReturn(Optional.of(request));

        Payment actual = createPaymentUseCaseImpl.apply(request);

        verify(repository).save(request);
        assertEquals(request, actual);
    }*/
}
