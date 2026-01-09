package com.example.payments.infraestructure.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Configuration
public class PaymentCreatedTopic {

    @Bean
    public NewTopic paymentCreatedTopicNew() {
        return TopicBuilder.name("payment-created")
                .partitions(1).replicas(1)
                .build();
    }

}
