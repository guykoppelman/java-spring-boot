package com.example.messageworker.service;

import com.example.messageworker.domain.Message;
import com.example.messageworker.domain.MessageCommand;
import com.example.messageworker.domain.MessageRepository;
import com.example.messageworker.domain.ProcessedEvent;
import com.example.messageworker.domain.ProcessedEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageCommandHandler {
    private final MessageRepository messages;
    private final ProcessedEventRepository processedEvents;

    public MessageCommandHandler(MessageRepository messages, ProcessedEventRepository processedEvents) {
        this.messages = messages;
        this.processedEvents = processedEvents;
    }

    @Transactional
    public void handle(MessageCommand command) {
        if (processedEvents.existsById(command.eventId())) return;

        switch (command.action()) {
            case CREATE -> messages.save(new Message(command.id(), command.msg()));
            case UPDATE -> messages.findById(command.id()).ifPresent(message -> message.update(command.msg()));
            case DELETE -> messages.findById(command.id()).ifPresent(messages::delete);
            case READ -> messages.findById(command.id());
        }
        processedEvents.save(new ProcessedEvent(command.eventId()));
    }
}