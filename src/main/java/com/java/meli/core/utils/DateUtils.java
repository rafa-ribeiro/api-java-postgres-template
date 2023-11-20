package com.java.meli.core.utils;

import java.time.LocalDate;

public class DateUtils {

    public static java.sql.Date convertToSQLDate(LocalDate originalDate) {
        if (originalDate == null) {
            return null;
        }
        return java.sql.Date.valueOf(originalDate);
    }

    public static LocalDate convertSQLDatetoLocalDate(java.sql.Date sqlDate) {
        return sqlDate.toLocalDate();
    }

}
