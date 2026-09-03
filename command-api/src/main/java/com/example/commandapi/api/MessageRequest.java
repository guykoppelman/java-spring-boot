package com.example.commandapi.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record MessageRequest(@Positive int id, @NotBlank String msg) { }