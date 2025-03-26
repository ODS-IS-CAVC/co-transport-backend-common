package com.next.logistic.s3.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "next.aws")
@Component
public class AwsConfigProperties {

    public  String accessKey ;
    public  String secretKey;
    public  String bucketName;
    public  String region;
    public  Long expiredTime;
}
