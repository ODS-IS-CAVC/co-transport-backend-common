package com.next.logistic.mail.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@EnableConfigurationProperties({NextMailProperties.class})
@Slf4j
public class NextMailConfiguration {

    @Autowired
    private NextMailProperties nextMailProperties;

    @Bean
    public JavaMailSender getJavaMailSender(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setHost(nextMailProperties.getHost());
        mailSender.setPort(nextMailProperties.getPort());
        mailSender.setUsername(nextMailProperties.getUsername());
        mailSender.setPassword(nextMailProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", nextMailProperties.getProtocol());
        props.put("mail.smtp.auth", nextMailProperties.getAuth());
        props.put("mail.smtp.starttls.enable", nextMailProperties.getStartTLS());
        props.put("mail.mime.charset", "UTF-8");

        return mailSender;
    }
}
