package com.java.meli.core.utils.cpf.exceptions;

public class CPFFirstDigitCalcException extends CPFValidatorException {

    public CPFFirstDigitCalcException() {
        super("Invalid argument to calc the CPF first digit");
    }
}
