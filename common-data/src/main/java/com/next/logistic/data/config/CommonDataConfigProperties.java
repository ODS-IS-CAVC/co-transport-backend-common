package com.next.logistic.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 共通データ設定プロパティクラス。
 *
 * @author Next Logistics
 */
@Data
@ConfigurationProperties(prefix = "next.data-common")
@Component
public class CommonDataConfigProperties {

    public String domainGateway;
    public String domainIX;
    public boolean mockData;
}
