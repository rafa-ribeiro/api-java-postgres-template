package com.java.meli.core.utils.cpf;

import com.java.meli.core.utils.cpf.exceptions.CPFDoesNotContainOnlyDigitsException;
import com.java.meli.core.utils.cpf.exceptions.CPFInvalidLengthException;
import com.java.meli.core.utils.cpf.exceptions.CPFWithAllRepeatedDigits;
import com.java.meli.core.utils.cpf.exceptions.EmptyCPFException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.AssertionErrors;

public class CPFValidatorTest {

    @Test
    void givenAValidCPF_whenDigitsAreValid_thenIsValidCPFReturnsTrue() throws Exception {
        String cpf = "30647374064";

        boolean result = CPFValidator.isValidCPF(cpf);
        AssertionErrors.assertTrue("CPF is not valid", result);
    }

    @Test
    void givenAInvalidCPF_whenDigitsAreInalid_thenIsValidCPFReturnsFalse() throws Exception {
        String cpf = "30647374073";

        boolean result = CPFValidator.isValidCPF(cpf);
        AssertionErrors.assertFalse("CPF is valid", result);
    }

    @Test
    void givenAInvalidCPF_whenCPFisEmpty_thenThrowError() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            CPFValidator.isValidCPF(null);
        });

        Assertions.assertInstanceOf(EmptyCPFException.class, exception);
    }

    @Test
    void givenAInvalidCPF_whenCPFContainsNotOnlyDigits_thenThrowError() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            String cpf = "306.473.740-64";
            CPFValidator.isValidCPF(cpf);
        });

        Assertions.assertInstanceOf(CPFDoesNotContainOnlyDigitsException.class, exception);
        String expectedMessage = "Be sure that CPF is not formatted for CPF digits validation";
        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }

    @Test
    void givenAInvalidCPF_whenCPFDoesNotHaveRequiredDigits_thenThrowError() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            String cpf = "3064737406";
            CPFValidator.isValidCPF(cpf);
        });

        Assertions.assertInstanceOf(CPFInvalidLengthException.class, exception);
    }

    @Test
    void givenAInvalidCPF_whenCPFHasAllRepeatedDigits_thenThrowError() {
        Exception exception = Assertions.assertThrows(Exception.class, () -> {
            String cpf = "11111111111";
            CPFValidator.isValidCPF(cpf);
        });

        Assertions.assertInstanceOf(CPFWithAllRepeatedDigits.class, exception);
    }

}
