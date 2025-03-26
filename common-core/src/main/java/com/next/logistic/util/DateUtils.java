package com.next.logistic.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DateUtils {
    private static final List<String> DATE_PATTERNS = Arrays.asList(
        "yyyy-MM-dd'T'HH:mm:ss", // Example: 2025-02-24T09:15:00
        "yyyy-MM-dd'T'HH:mm",     // Example: 2025-02-24T09:15
        "yyyy-MM-dd HH:mm:ss",    // Example: 2025-02-24 09:15:00
        "yyyy-MM-dd HH:mm",       // Example: 2025-02-24 09:15
        "yyyy-MM-dd",             // Example: 2025-02-24
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ", // Example: 2025-02-24T09:15:00.000+0900
        "yyyy-MM-dd'T'HH:mm:ssZ",     // Example: 2025-04-28T02:00:00+0900
        "yyyy-MM-dd'T'HH:mmZ"         // Example: 2025-04-28T02:00+0900
    );

    private static final List<String> TIME_PATTERNS = Arrays.asList("HHmm", "HH:mm", "HH:mm:ss", "HHmmss");

    public static String formatDateTime(String dateTime) {
        LocalDateTime localDateTime = parseDate(dateTime);
        return localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static LocalDateTime parseDate(String dateStr) {
        for (String pattern : DATE_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDateTime.parse(dateStr, formatter);
            } catch (Exception e) {
                continue;
            }
        }
        throw new IllegalArgumentException("Unable to parse date string: " + dateStr);
    }

    public static LocalTime parseTime(String timeString) {
        if (timeString == null) {
            return null;
        }
        for (String pattern : TIME_PATTERNS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalTime.parse(timeString, formatter);
            } catch (DateTimeParseException e) {
                continue;
            }
        }
        System.err.println("Error parsing time: " + timeString);
        return null;
    }

    public static String adjustDateIfNeeded(String startDate, String endDate) {
        if (startDate == null || endDate == null) {
            return endDate;
        }

        LocalDateTime dateTimeStart = parseDate(startDate);
        LocalDateTime dateTimeEnd = parseDate(endDate);

        if (dateTimeEnd.isEqual(dateTimeStart) || dateTimeEnd.isBefore(dateTimeStart)) {
            dateTimeEnd = dateTimeEnd.plus(1, ChronoUnit.DAYS);
        }

        return dateTimeEnd.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public static LocalDateTime[] getStartAndEndDateTime(LocalDate startDate, LocalTime startLocalTime, LocalTime endLocalTime) {
        // Create LocalDateTime objects
        LocalDateTime startDateTime = LocalDateTime.of(startDate, startLocalTime);
        LocalDateTime endDateTime = LocalDateTime.of(startDate, endLocalTime);

        // If endDateTime is smaller than startDateTime, add 1 day to endDateTime
        if (endDateTime.isBefore(startDateTime)) {
            endDateTime = endDateTime.plusDays(1);
        }

        return new LocalDateTime[]{startDateTime, endDateTime};
    }
}
