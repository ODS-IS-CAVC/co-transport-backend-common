package com.next.logistic.data.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestCaseConstant {

    // Error codes
    public static final int ERROR_CODE_0 = 1000;  // Normal
    public static final int ERROR_CODE_1 = 1001;  // Delay 30'
    public static final int ERROR_CODE_2 = 1002;  // Delay 3h
    public static final int ERROR_CODE_3 = 1003;  // Stop due to strong wind
    public static final int ERROR_CODE_4 = 1004;  // Delay 4h due to traffic
    public static final int ERROR_CODE_5 = 1005;  // Delay all day due to traffic

    /**
     * Simulate returning error code based on input date and time
     */
    public static int simulateErrorCode(LocalDate day, LocalTime departureTime, LocalTime arrivalTime) {
        // 2025/02/25
        if (day.equals(LocalDate.of(2025, 2, 25))) {
            // Afternoon trip (13h-18h) - Normal
            if (departureTime.equals(LocalTime.of(13, 0)) && arrivalTime.equals(LocalTime.of(18, 0))) {
                return ERROR_CODE_0;
            }
            // Night trip (19h-23h) - Rain 25mm/h
            if (departureTime.equals(LocalTime.of(19, 0)) && arrivalTime.equals(LocalTime.of(23, 0))) {
                return ERROR_CODE_1;  // Delay 30'
            }
        }

        // 2025/02/26
        if (day.equals(LocalDate.of(2025, 2, 26))) {
            // Morning trip (6h-9h) - Rain = 50mm/h
            if (departureTime.equals(LocalTime.of(7, 0)) && arrivalTime.equals(LocalTime.of(9, 45))) {
                return ERROR_CODE_2;  // Delay 3h
            }
            // Morning trip after delay (9h-12h) - Normal
            if (departureTime.equals(LocalTime.of(10, 0)) && arrivalTime.equals(LocalTime.of(12, 0))) {
                return ERROR_CODE_0;
            }
            // Afternoon trip (11h-16h) - Wind >20m/s
            if (departureTime.equals(LocalTime.of(12, 0)) && arrivalTime.equals(LocalTime.of(14, 45))) {
                return ERROR_CODE_3;  // Stop due to strong wind
            }
            // Night trip (14h-18h) - Normal
            if (departureTime.equals(LocalTime.of(14, 0)) && arrivalTime.equals(LocalTime.of(16, 0))) {
                return ERROR_CODE_0;
            }
            // Afternoon trip (13h-18h) - Traffic issues
            if (departureTime.equals(LocalTime.of(19, 0)) && arrivalTime.equals(LocalTime.of(20, 0))) {
                return ERROR_CODE_5;  // Delay all day due to traffic
            }
        }
        // 2025/02/27
        if (day.equals(LocalDate.of(2025, 2, 27))) {
            // Morning trip (6h-9h) - Normal
            if (departureTime.equals(LocalTime.of(9, 0)) && arrivalTime.equals(LocalTime.of(12, 0))) {
                return ERROR_CODE_0;
            }
            // Afternoon trip (13h-18h) - Wind 10-20m/s
            if (departureTime.equals(LocalTime.of(13, 0)) && arrivalTime.equals(LocalTime.of(18, 0))) {
                return ERROR_CODE_1;  // Delay 30'
            }
        }

        return ERROR_CODE_0;
    }
}
