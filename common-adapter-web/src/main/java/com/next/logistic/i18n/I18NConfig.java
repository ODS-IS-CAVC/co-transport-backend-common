package com.next.logistic.i18n;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class I18NConfig {

    @Bean
    @Order(Ordered.LOWEST_PRECEDENCE)
    public MessagesResourceBundle messagesResourceBundle() {
        return new MessagesResourceBundle();
    }

    @Bean
    public MessageSource messageSource() {

        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        ClassLoader cl = this.getClass().getClassLoader();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(cl);
        messageSource.setResourceLoader(resolver);
        messageSource.setBasenames("classpath:i18n-core/messages", "classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
