package com.next.logistic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "next.telemetry")
@Data
public class OpenTelemetryProperties {

    private String jaegerUrl;
}
