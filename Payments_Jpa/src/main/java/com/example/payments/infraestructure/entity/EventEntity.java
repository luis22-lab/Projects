package com.example.payments.infraestructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "processed_event")
@AllArgsConstructor
@NoArgsConstructor
public class EventEntity {

    @Id
    @Column(name = "event_id", nullable= false)
    private String id;

    @Column(name = "processed_at", nullable = false, updatable = false)
    private Instant processed;
}
