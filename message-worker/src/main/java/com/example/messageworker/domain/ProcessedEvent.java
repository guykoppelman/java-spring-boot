package com.example.messageworker.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class ProcessedEvent {
    @Id
    private UUID id;

    protected ProcessedEvent() { }
    public ProcessedEvent(UUID id) { this.id = id; }
}