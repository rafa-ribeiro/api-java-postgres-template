package com.java.meli.user.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.AssertionErrors;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class UserAgeServiceTest {

    @Test
    void givenADate_whenUserIsOver18_thenMinimumAgeOk() {
        Date userBirth = new GregorianCalendar(2000, Calendar.MARCH, 21).getTime();
        boolean hasMinimumAge = UserAgeService.hasMinimumAge(userBirth, 18);
        AssertionErrors.assertTrue("User is under the minimum age", hasMinimumAge);
    }

    @Test
    void givenADate_whenUserIsUnder18_thenMinimumAgeNotOk() {
        Date userBirth = new GregorianCalendar(2010, Calendar.MARCH, 21).getTime();
        boolean hasMinimumAge = UserAgeService.hasMinimumAge(userBirth, 18);
        AssertionErrors.assertFalse("User is over the minimum age", hasMinimumAge);
    }

    @Test
    void givenADate_whenUserIsExact18_thenMinimumAgeOk() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime eighteenYearsAgo = now.minusYears(18);
        Date userBirth = Date.from(eighteenYearsAgo.atZone(ZoneId.systemDefault()).toInstant());

        boolean hasMinimumAge = UserAgeService.hasMinimumAge(userBirth, 18);
        AssertionErrors.assertTrue("User is under the minimum age", hasMinimumAge);
    }

    @Test
    void givenADate_whenUserIsAlmost18_thenMinimumAgeNotOk() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime eighteenYearsAgo = now.minusYears(18).plusDays(1);
        Date userBirth = Date.from(eighteenYearsAgo.atZone(ZoneId.systemDefault()).toInstant());

        boolean hasMinimumAge = UserAgeService.hasMinimumAge(userBirth, 18);
        AssertionErrors.assertFalse("User is over the minimum age", hasMinimumAge);
    }
}
