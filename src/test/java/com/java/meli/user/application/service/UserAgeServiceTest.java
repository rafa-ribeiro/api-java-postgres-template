package com.java.meli.user.application.service;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.AssertionErrors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;


public class UserAgeServiceTest {

    @Test
    void givenADate_whenUserIsOver18_thenMinimumAgeOk() {
        LocalDate userBirth = LocalDate.of(2000, Calendar.MARCH, 21);
        boolean hasMinimumAge = UserAgeService.hasMinimumAge(userBirth, 18);
        AssertionErrors.assertTrue("User is under the minimum age", hasMinimumAge);
    }

    @Test
    void givenADate_whenUserIsUnder18_thenMinimumAgeNotOk() {
        LocalDate userBirth = LocalDate.of(2010, Calendar.MARCH, 21);
        boolean hasMinimumAge = UserAgeService.hasMinimumAge(userBirth, 18);
        AssertionErrors.assertFalse("User is over the minimum age", hasMinimumAge);
    }

    @Test
    void givenADate_whenUserIsExact18_thenMinimumAgeOk() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime eighteenYearsAgo = now.minusYears(18);
        LocalDate userBirth = eighteenYearsAgo.toLocalDate();
        boolean hasMinimumAge = UserAgeService.hasMinimumAge(userBirth, 18);
        AssertionErrors.assertTrue("User is under the minimum age", hasMinimumAge);
    }

    @Test
    void givenADate_whenUserIsAlmost18_thenMinimumAgeNotOk() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime almostEighteenYearsAgo = now.minusYears(18).plusDays(1);
        LocalDate userBirth = almostEighteenYearsAgo.toLocalDate();
        boolean hasMinimumAge = UserAgeService.hasMinimumAge(userBirth, 18);
        AssertionErrors.assertFalse("User is over the minimum age", hasMinimumAge);
    }
}
