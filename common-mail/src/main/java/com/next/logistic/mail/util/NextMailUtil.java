package com.next.logistic.mail.util;

import com.next.logistic.mail.config.NextMailConfiguration;
import com.next.logistic.mail.config.NextMailProperties;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

@Slf4j
@Service
@RequiredArgsConstructor
public class NextMailUtil {

    @Autowired
    private NextMailProperties nextMailProperties;

    @Autowired
    private NextMailConfiguration nextMailConfiguration;

    private final SpringTemplateEngine templateEngine;

    private final String EMAIL_SYSTEM_CC = "systemskyodoyuso@gmail.com";

    @Async
    public void sendMailByType(String to, String mailType, List<String> data) {
        Context context = new Context();
        
        context.setVariable("conveyanceNumber", data.get(0));  // 便名
        context.setVariable("route", data.get(1));            // 経路
        context.setVariable("departureTime", data.get(2));    // 出発時刻

        MailType type = MailType.valueOf(mailType);

        switch (type) {
            case WEATHER_STOP:
                context.setVariable("delayTime", data.get(3));  // 停止時間
                break;

            case TRAFFIC_STOP:
                context.setVariable("delayTime", data.get(3));     // 遅延時間
                context.setVariable("arrivalTime", data.get(4));   // 到着時刻
                break;

            case INCIDENT_STOP:
                context.setVariable("delayTime", data.get(3));      // 停止時間
                context.setVariable("arrivalTime", data.get(4));   // 到着時刻
                break;

            case TIME_CHANGE:
                break;
            
            case POLICE_INCIDENT:
                context.setVariable("vehicleNumber", data.get(3));  // 車両番号
                context.setVariable("incidentId", data.get(4));      // 事象ID
                break;
            
            case POLICE_INCIDENT_STOP:
                context.setVariable("vehicleNumber", data.get(3));  // 車両番号
                context.setVariable("incidentId", data.get(4));      // 事象ID
                break;

            default:
                log.error("Invalid mail type: {}", mailType);
                return;
        }
        sendMail(to, type.getSubject(), context, type.getTemplate());
    }

    public void sendMail(String to, String subject, Context context, String template) {
        log.info("Start send mail to: {} subject: {}", to, subject);
        JavaMailSender mailSender = nextMailConfiguration.getJavaMailSender();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            // Specify UTF-8 encoding
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            String html = templateEngine.process(template, context);

            helper.setFrom(nextMailProperties.getFrom());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            helper.addCc(EMAIL_SYSTEM_CC);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        log.info("End send mail to: {} subject: {}", to, subject);
    }

    public boolean sendMail(String to, String subject, String content, String template) {
        log.info("Start send mail to: {} subject: {}", to, subject);
        JavaMailSender mailSender = nextMailConfiguration.getJavaMailSender();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            // Specify UTF-8 encoding
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            Context context = new Context();
            context.setVariable("content", content);
            String html = templateEngine.process(template, context);

            helper.setFrom(nextMailProperties.getFrom());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);
            helper.addCc(EMAIL_SYSTEM_CC);
            mailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
        log.info("End send mail to: {} subject: {}", to, subject);
        return true;
    }

}
