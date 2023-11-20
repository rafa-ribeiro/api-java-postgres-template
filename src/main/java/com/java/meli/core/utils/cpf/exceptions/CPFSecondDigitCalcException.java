package com.java.meli.core.utils.cpf.exceptions;

public class CPFSecondDigitCalcException extends CPFValidatorException {

    public CPFSecondDigitCalcException() {
        super("Invalid argument to calc the CPF second digit");
    }
}
