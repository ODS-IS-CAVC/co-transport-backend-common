package com.next.logistic.s3.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Autowired
    private AwsConfigProperties awsConfigProperties;

    @Bean
    public AmazonS3 getS3Client() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsConfigProperties.getAccessKey(),
                awsConfigProperties.getSecretKey());
        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion(Regions.AP_NORTHEAST_1)
                .build();
    }
}