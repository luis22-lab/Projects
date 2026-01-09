package com.example.payments.steps;

import com.example.payments.domain.entity.PaymentBean;
import com.example.payments.domain.enums.PaymentMethod;
import com.example.payments.domain.enums.PaymentStatusEnum;
import com.example.payments.infraestructure.entity.PaymentEntity;
import com.example.payments.infraestructure.mapper.PaymentEntityMapper;
import com.example.payments.infraestructure.repository.PaymentJpaRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class PaymentRepositorySteps {

    @Autowired
    private PaymentJpaRepository paymentJpaRepository;

    private PaymentBean payment;

    @Given("no existe el Pago")
    public void limpiarPagos() {
        paymentJpaRepository.deleteAll();
    }

    @When("registro un pago")
    public void savePayment() {
                System.out.println("-*-***************************************-*-CUCUMBER *-***********************************************************************************************************************************");
                 payment = PaymentBean.builder()
                .destinationId(1L)
                .requestId("56789")
                .paymentMethod(PaymentMethod.CHECK)
                .origin(2L)
                .dateValue(LocalDateTime.now())
                .createDate(LocalDateTime.now())
                .amount(BigDecimal.TWO)
                .status(PaymentStatusEnum.DONE)
                .build();
        final var saved = paymentJpaRepository.save(PaymentEntityMapper.INSTANCE.mapBeanToEntity(payment));
    }

    @Then("el pago debería estar registrado")
    public void verifyPayment() {
        Optional<PaymentEntity> result = paymentJpaRepository.findOneByRequestId("56789");
        assertThat(result).isPresent();
        assertThat(result.get().getAmount()).isEqualTo(payment.getAmount());
        assertThat(result.get().getPaymentMethod()).isEqualTo(PaymentMethod.CHECK);
    }


}
