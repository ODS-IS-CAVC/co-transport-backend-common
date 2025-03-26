package com.next.logistic.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "next.kafka")
public class KafkaProperties {

    private String groupId;
    private String bootstrapConfig;

}
