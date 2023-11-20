package com.java.meli.core.utils.cpf;

import com.java.meli.core.utils.cpf.exceptions.*;

import java.util.regex.Pattern;

public class CPFValidator {

    private static final int CPF_LENGTH = 11;

    private static final int CPF_DIGITS_LENGTH = 2;

    public static boolean isValidCPF(String cpf) throws CPFValidatorException {
        checkCPFIsEmpty(cpf);
        checkCPFContainsOnlyDigits(cpf);
        checkCPFLength(cpf);
        checkCPFHasAllRepeatedDigits(cpf);

        String cpfWithoutDigits = cpf.substring(0, CPF_LENGTH - CPF_DIGITS_LENGTH);
        int firstDigit = calcFirstDigit(cpfWithoutDigits);
        int secondDigit = calcSecondDigit(cpfWithoutDigits, firstDigit);

        String expectedCPF = cpfWithoutDigits + firstDigit + secondDigit;
        return cpf.equals(expectedCPF);
    }

    private static int calcFirstDigit(String cpf) throws CPFFirstDigitCalcException {
        int cpfWithoutDigitsLen = CPF_LENGTH - CPF_DIGITS_LENGTH;
        if (cpf.length() != cpfWithoutDigitsLen) {
            throw new CPFFirstDigitCalcException();
        }

        return calcDigit(cpf);
    }

    private static int calcSecondDigit(String cpf, int firstDigit) throws CPFSecondDigitCalcException {
        String mountedCPF = cpf + firstDigit;
        if (mountedCPF.length() != CPF_LENGTH - 1) {
            throw new CPFSecondDigitCalcException();
        }

        return calcDigit(mountedCPF);
    }


    private static int calcDigit(String cpf) {
        int calcDigitWeight = 2;
        int digitsWeightTotal = 0;

        for (int i = cpf.length() - 1; i >= 0; i--) {
            int dig = Character.getNumericValue(cpf.charAt(i));
            digitsWeightTotal += dig * calcDigitWeight;
            calcDigitWeight++;
        }
        int remainder = digitsWeightTotal % CPF_LENGTH;
        return remainder < 2 ? 0 : CPF_LENGTH - remainder;
    }

    private static void checkCPFIsEmpty(String cpf) throws EmptyCPFException {
        if (cpf == null) {
            throw new EmptyCPFException();
        }
    }


    private static void checkCPFContainsOnlyDigits(String cpf) throws CPFDoesNotContainOnlyDigitsException {
        String onlyDigitsRegex = "^[0-9]+$";
        Pattern pattern = Pattern.compile(onlyDigitsRegex);

        boolean cpfContainsOnlyDigits = pattern.matcher(cpf).matches();
        if (!cpfContainsOnlyDigits) {
            throw new CPFDoesNotContainOnlyDigitsException();
        }
    }

    private static void checkCPFLength(String cpf) throws CPFInvalidLengthException {
        if (cpf.length() != CPF_LENGTH) {
            throw new CPFInvalidLengthException();
        }
    }

    private static void checkCPFHasAllRepeatedDigits(String cpf) throws CPFWithAllRepeatedDigits {
        if (hasAllRepeatedDigits(cpf)) {
            throw new CPFWithAllRepeatedDigits();
        }
    }

    private static boolean hasAllRepeatedDigits(String cpf) {
        for (int i = 1; i < cpf.length(); i++) {
            if (cpf.charAt(0) != cpf.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
