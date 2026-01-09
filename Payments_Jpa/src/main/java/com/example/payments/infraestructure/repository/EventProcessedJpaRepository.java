package com.example.payments.infraestructure.repository;

import com.example.payments.infraestructure.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventProcessedJpaRepository  extends JpaRepository<EventEntity,String> {
}
