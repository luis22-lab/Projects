package com.example.payments.application.usecase;

import com.example.payments.application.predicate.requestpayment.ValidateRequestPaymentImpl;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.event.PaymentEventPort;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.repository.AccountRepository;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.domain.usecase.CreatePaymentUseCase;
import com.example.payments.infraestructure.event.CreateProducerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreatePaymentUseCaseImpl implements CreatePaymentUseCase {

    public static final int status_422 = 422;
    private final PaymentRepository paymentRepository;
    private final AccountRepository accountRepository;
    private final ValidateRequestPaymentImpl validateRequestPayment;
    private final CreateProducerService producerService;
    private final ApplicationEventPublisher publisher;
    private final PaymentEventPort paymentEventPort;
    @Override
    public Payment apply(Payment payment) {

        final var exists = paymentRepository.findByRequestId(payment.getRequestId());
        exists.ifPresent(existPayment -> {throw new DomainException(status_422,"Existing Request Payment");});
        final var acc = accountRepository.findByIdAccount(payment.getDestinationId()).orElseThrow(() -> new DomainException(status_422,"Account Destinity not found"));
        Optional<String> validateError = validateRequestPayment.validateRequestPayment(payment);
        validateError.ifPresent(errorMsg -> {throw new DomainException(status_422,errorMsg);});
        return  paymentRepository.save(payment).orElseThrow(() -> new DomainException(status_422,"Error to save payment"));
        //producerService.senPayment(result);
        //paymentEventPort.sendPayment(result);
        //publisher.publishEvent(new CreatePaymentEvent(result));
        //return result;
    }

}


