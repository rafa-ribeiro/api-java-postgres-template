package com.java.meli.user.application.service.impl;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;


@Service
public class UserAgeServiceImpl {


    public boolean hasMinimumAge(Date userBirth, int minimumAge) {
        LocalDate now = LocalDate.now();
        LocalDate birth = userBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(birth, now);
        int years = Math.abs(period.getYears());
        return years >= minimumAge;
    }
}
