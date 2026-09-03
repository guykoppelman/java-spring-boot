package com.example.messageworker.domain;

import java.time.Instant;
import java.util.UUID;

public record MessageCommand(UUID eventId, Action action, int id, String msg, Instant occurredAt) { }