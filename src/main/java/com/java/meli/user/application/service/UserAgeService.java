package com.java.meli.user.application.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class UserAgeService {


    public static boolean hasMinimumAge(Date userBirth, int minimumAge) {
        LocalDate now = LocalDate.now();
        LocalDate birth = userBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(birth, now);
        int years = Math.abs(period.getYears());
        return years >= minimumAge;
    }

}
