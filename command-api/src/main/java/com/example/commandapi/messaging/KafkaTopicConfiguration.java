package com.example.commandapi.messaging;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.lang.NonNull;

@Configuration
public class KafkaTopicConfiguration {
    private static final int PARTITIONS = 12;

    @Bean
    NewTopic createTopic() { return topic("messages.create"); }

    @Bean
    NewTopic updateTopic() { return topic("messages.update"); }

    @Bean
    NewTopic deleteTopic() { return topic("messages.delete"); }

    @Bean
    NewTopic readTopic() { return topic("messages.read"); }

    private NewTopic topic(@NonNull String name) {
        return TopicBuilder.name(name).partitions(PARTITIONS).replicas(1).build();
    }
}