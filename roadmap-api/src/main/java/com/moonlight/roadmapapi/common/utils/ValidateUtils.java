package com.moonlight.roadmapapi.common.utils;

import com.amazonaws.util.StringUtils;
import com.moonlight.roadmapapi.common.exception.RoadmapException;
import com.moonlight.roadmapapi.common.exception.ValidationException;
import com.moonlight.roadmapapi.entity.RoadmapResponseStatus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class ValidateUtils {
    private ValidateUtils() {
    }

    private static final String YMD_DATE_FORMAT = "yyyy/MM/dd";

    public static <T> void notNull(T value, String paramName) throws ValidationException {
        if (value == null) {
            throw new ValidationException(paramName + " should not be null");
        }
    }

    public static <T> void internalNotNull(T value) throws RoadmapException {
        if (value == null) {
            throw new RoadmapException(RoadmapResponseStatus.SYSTEM_INTERNAL_ERROR);
        }
    }

    public static void notNullOrEmpty(String value, String paramName) throws ValidationException {
        if (value == null || value.isEmpty()) {
            throw new ValidationException(paramName + " should not be null or empty");
        }
    }

    public static void min(int value, int minimum, String paramName) throws ValidationException {
        if (value < minimum) {
            throw new ValidationException(paramName + " minimum is " + minimum);
        }
    }

    public static void doubleMin(double value, double minimum, String paramName) throws ValidationException {
        if (value < minimum) {
            throw new ValidationException(paramName + " minimum is " + minimum);
        }
    }

    public static Date parseDate(String dateString) throws ValidationException, ParseException {
        return parseDate(dateString, null, false);
    }

    public static Date parseDate(String dateString, Date defaultDate, boolean isThrowException) throws ValidationException, ParseException {
        Date date;
        if (StringUtils.isNullOrEmpty(dateString)) {
            if (isThrowException) {
                throw new ValidationException("parse dateString error");
            }
            return defaultDate;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(YMD_DATE_FORMAT);
        date = formatter.parse(dateString);
        Date validDate = Optional.of(date).orElse(defaultDate);
        if (validDate == defaultDate && isThrowException) {
            throw new ValidationException("parse date error");
        }
        return validDate;
    }

    public static class Friendly {
        private Friendly() {
        }

        public static void assertTrue(boolean condition, String friendlyMessage) throws ValidationException {
            if (!condition) {
                throw new ValidationException(friendlyMessage);
            }
        }

        public static void assertFalse(boolean condition, String friendlyMessage) throws ValidationException {
            if (condition) {
                throw new ValidationException(friendlyMessage);
            }
        }
    }
}
