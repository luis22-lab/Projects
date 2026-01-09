package com.example.payments.infraestructure.entity;

import com.example.payments.domain.enums.PaymentMethodEnum;
import com.example.payments.domain.enums.PaymentStatusEnum;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "payment")
@Data
public class PaymentEntity {


    @Id
    private String id;

    @Field
    private String requestId;

    @Field
    private Long idOrigin;

    @Field
    private Long idDestination;

    @Field
    private BigDecimal amount;

    @Field
    private LocalDateTime dateCreated;

    @Field
    private LocalDateTime dateValue;

    @Field
    private PaymentMethodEnum paymentMethod;

    @Field
    private PaymentStatusEnum paymentStatus;
    
}
