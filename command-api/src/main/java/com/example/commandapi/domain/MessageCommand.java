package com.example.commandapi.domain;

import java.time.Instant;
import java.util.UUID;

public record MessageCommand(UUID eventId, Action action, int id, String msg, Instant occurredAt) {
    public static MessageCommand of(Action action, int id, String msg) {
        return new MessageCommand(UUID.randomUUID(), action, id, msg, Instant.now());
    }
}