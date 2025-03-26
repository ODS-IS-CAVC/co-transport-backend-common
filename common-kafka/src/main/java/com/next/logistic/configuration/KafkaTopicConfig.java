package com.next.logistic.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic exampleTopic() {
        return new NewTopic("example-topic", 3, (short) 1);
    }
}
