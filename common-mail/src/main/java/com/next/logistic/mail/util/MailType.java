package com.next.logistic.mail.util;

import lombok.Getter;

/**
 * <PRE>
 * メールタイプ列挙型。<BR>
 * </PRE>
 *
 * @author Next Logistics
 */
@Getter
public enum MailType {
    WEATHER_STOP("weather-stop-template", "【共同輸送システム】悪天候により輸送トラックが停止"),
    TRAFFIC_STOP("traffic-stop-template", "【共同輸送システム】遅延が発生しました"),
    INCIDENT_STOP("incident-stop-template", "【共同輸送システム】車両緊急停止が発生しました"),
    TIME_CHANGE("time-change-template", "【共同輸送システム】トラックの出発時刻が変更されました"),
    POLICE_INCIDENT("police-incident-template", "【運行管理システム】車両緊急停止が発生"),
    POLICE_INCIDENT_STOP("police-incident-stop-template", "【運行管理システム】悪天候により輸送トラックが停止");

    private final String template;
    private final String subject;

    MailType(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
