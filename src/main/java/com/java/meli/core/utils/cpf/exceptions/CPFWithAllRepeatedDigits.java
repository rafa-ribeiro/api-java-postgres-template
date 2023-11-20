package com.java.meli.core.utils.cpf.exceptions;

public class CPFWithAllRepeatedDigits extends CPFValidatorException {

    public CPFWithAllRepeatedDigits() {
        super("CPF is invalid. All digits are the same");
    }
}
