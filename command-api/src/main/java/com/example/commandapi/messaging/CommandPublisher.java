package com.example.commandapi.messaging;

import com.example.commandapi.domain.MessageCommand;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CommandPublisher {
    private final KafkaTemplate<String, MessageCommand> kafkaTemplate;

    public CommandPublisher(KafkaTemplate<String, MessageCommand> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(@NonNull MessageCommand command) {
        kafkaTemplate.send("messages." + command.action().name().toLowerCase(), Integer.toString(command.id()), command);
    }
}