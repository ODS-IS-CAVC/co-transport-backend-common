package com.next.logistic.mail.proxy;

import com.next.logistic.proxy.EnableNextCore;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.next.logistic.mail"})
@EnableNextCore
public class AdapterMailConfig {
}
