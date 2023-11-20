package com.java.meli.user.application.service;

import java.time.LocalDate;
import java.time.Period;

public class UserAgeService {


    public static boolean hasMinimumAge(LocalDate userBirth, int minimumAge) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(userBirth, now);
        int years = Math.abs(period.getYears());
        return years >= minimumAge;
    }

}
