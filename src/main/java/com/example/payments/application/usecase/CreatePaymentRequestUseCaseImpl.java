package com.example.payments.application.usecase;

import com.example.payments.application.mapper.PaymentMapper;
import com.example.payments.application.mapper.PaymentRequestMapper;
import com.example.payments.application.predicate.requestpaymentmultiple.ValidateRequestPaymentToMultipleImpl;
import com.example.payments.domain.entity.Payment;
import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.entity.PaymentRequestBean;
import com.example.payments.domain.entity.PaymentRequestDetail;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.repository.PaymentRepository;
import com.example.payments.domain.repository.PaymentRequestRepository;
import com.example.payments.domain.usecase.CreatePaymentRequestUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CreatePaymentRequestUseCaseImpl implements CreatePaymentRequestUseCase {

    public static final int status_422 = 422;
    private final PaymentRequestRepository repository;
    private final ValidateRequestPaymentToMultipleImpl validate;
    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final PaymentRequestMapper paymentRequestMapper;

    @Override
    public PaymentRequest apply(PaymentRequest paymentRequest) {

        PaymentRequestBean requestDone= (PaymentRequestBean) processRequest(paymentRequest);
        final var paymentDone = processPayments(paymentRequest.getPaymentRequestDetails());
        final var paymentsDetail = paymentDone.stream().map(paymentMapper::mapPaymentToPaymentRequestDetail).toList();
        return  paymentRequestMapper.mapToBeanRequest_Detail(requestDone,paymentsDetail);
    }


    private PaymentRequest processRequest(PaymentRequest paymentRequest) {

        final var exists = repository.findByRequestId(paymentRequest.getRequestId());
        exists.ifPresent(existsRequest -> {throw new DomainException(status_422,"Exists request Payment");});
        paymentRequest.getPaymentRequestDetails().stream().findAny().orElseThrow(()-> new DomainException(status_422,"Empty Payment List in the Request"));
        Optional<String>validateError = validate.validateRequestPaymentToMultiple(paymentRequest);
        validateError.ifPresent(errorMsg -> {throw new DomainException(status_422,errorMsg);});
        return repository.save(paymentRequest).orElseThrow(()-> new DomainException(status_422,"Payment Request"));
    }

    private  List<Payment> processPayments(List<PaymentRequestDetail> payments) {

        return payments.stream()
                .map(paymentMapper::mapPaymentDetailToPaymentBean)
                .map(paymentRepository::save)
                .flatMap(Optional::stream)
                .toList();
    }


}
