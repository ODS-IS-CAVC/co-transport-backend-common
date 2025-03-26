package com.next.logistic.security.config;

import com.next.logistic.authorization.UserContext;
import com.next.logistic.security.whitelist.AuthWhitelistProvider;
import com.next.logistic.security.whitelist.DefaultAuthWhitelistProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.*;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.context.WebApplicationContext;

@Configuration
@EnableRetry
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserContext userContext() {
        return new UserContext();
    }

    @Bean
    @ConditionalOnMissingBean(NextAuthenticationFilter.class)
    @Order(Ordered.LOWEST_PRECEDENCE)
    public NextAuthenticationFilter nextAuthenticationFilter() {
        return new NextAuthenticationFilter();
    }


    @Bean
    @ConditionalOnMissingBean(AuthWhitelistProvider.class)
    public AuthWhitelistProvider authWhitelistProvider() {
        return new DefaultAuthWhitelistProvider();
    }

}
