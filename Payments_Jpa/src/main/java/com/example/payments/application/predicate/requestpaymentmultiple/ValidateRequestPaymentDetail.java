package com.example.payments.application.predicate.requestpaymentmultiple;

import com.example.payments.application.mapper.PaymentRequestDetailMapper;
import com.example.payments.application.predicate.requestpayment.ValidateRequestPaymentImpl;
import com.example.payments.domain.entity.PaymentRequest;
import com.example.payments.domain.exception.DomainException;
import com.example.payments.domain.predicate.ValidateRequestPaymentToMultiple;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidateRequestPaymentDetail implements ValidateRequestPaymentToMultiple {

    private PaymentRequestDetailMapper map;
    private ValidateRequestPaymentImpl validateRequestPayment;

    @Override
    public boolean test(PaymentRequest paymentRequest) {
        return validatePaymentRequestDetail(paymentRequest);
    }

    private boolean validatePaymentRequestDetail(PaymentRequest paymentRequest) {
        final var  status_422 = 422;
        final var  paymentDetail = paymentRequest.getPaymentRequestDetails();
            paymentDetail.stream()
                    .forEach(payment -> {
                        Optional<String> error =  validateRequestPayment.validateRequestPayment(map.mapPaymentRequestDetailToPaymentBean(payment));
                        error.ifPresent(errMsg -> {
                        throw new DomainException(status_422,"Payment id " + payment.getOrigin() + ": " + errMsg);
                    });
                });
        return true;
    }

    @Override
    public String getErrorMessage() {
        return "Error al crear pago en la lista";
    }
}
