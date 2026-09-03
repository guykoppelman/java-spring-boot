package com.example.messageworker.messaging;

import com.example.messageworker.domain.MessageCommand;
import com.example.messageworker.service.MessageCommandHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class CommandListener {
    private final MessageCommandHandler handler;

    public CommandListener(MessageCommandHandler handler) { this.handler = handler; }

    @KafkaListener(topics = {"messages.create", "messages.update", "messages.delete", "messages.read"}, groupId = "message-worker")
    public void consume(MessageCommand command) { handler.handle(command); }
}