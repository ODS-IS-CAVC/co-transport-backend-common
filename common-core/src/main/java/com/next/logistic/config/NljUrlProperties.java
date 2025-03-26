package com.next.logistic.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "next.url")
public class NljUrlProperties {

    private String semiDynamicInfo;
    private String mobilityHub;
    private String incidentInfo;
    private String trackBySipGateway;
    private boolean mockData;
    private boolean mobilityHubMockData;

    private String incidentInfoYamato;
    private String incidentInfoYamatoTracking;
    private String trackBySipTtmi;
    private String ttmi;

    private String apiKey;
    private String loginData;
    private String loginSystem;
    private String verifyToken;
    private String domainTransaction;
    private String domainCarrier;
    private String domainShipper;
    private String domainIx;
    private String domainIxDemand;
    private String domainGateway;

}
