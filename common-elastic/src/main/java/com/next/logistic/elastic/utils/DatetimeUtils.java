package com.next.logistic.elastic.utils;

import java.text.SimpleDateFormat;

public class DatetimeUtils {

    public static final String DATETIME_FORMAT = "dd-MM-yyyy HH:mm";
    public static final String DATETIME_FORMAT_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat(
            DATETIME_FORMAT_Z);


}
