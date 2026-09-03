package com.example.commandapi.api;

import com.example.commandapi.domain.Action;
import com.example.commandapi.domain.MessageCommand;
import com.example.commandapi.messaging.CommandPublisher;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final CommandPublisher publisher;

    public MessageController(CommandPublisher publisher) { this.publisher = publisher; }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Queue a create command")
    void create(@Valid @RequestBody MessageRequest request) { publish(Action.CREATE, request.id(), request.msg()); }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Queue an update command")
    void update(@PathVariable @Positive int id, @Valid @RequestBody MessageRequest request) { publish(Action.UPDATE, id, request.msg()); }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Queue a delete command")
    void delete(@PathVariable @Positive int id) { publish(Action.DELETE, id, null); }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @Operation(summary = "Queue a read command")
    void read(@PathVariable @Positive int id) { publish(Action.READ, id, null); }

    private void publish(Action action, int id, String msg) { publisher.publish(MessageCommand.of(action, id, msg)); }
}