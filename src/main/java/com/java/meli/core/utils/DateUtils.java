package com.java.meli.core.utils;

import java.util.Date;

public class DateUtils {

    public static java.sql.Date convertToSQLDate(Date originalDate) {
        if (originalDate == null) {
            return null;
        }
        return new java.sql.Date(originalDate.getTime());
    }

}
