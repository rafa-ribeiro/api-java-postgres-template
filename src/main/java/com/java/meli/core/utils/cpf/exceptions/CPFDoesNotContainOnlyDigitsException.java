package com.java.meli.core.utils.cpf.exceptions;

public class CPFDoesNotContainOnlyDigitsException extends CPFValidatorException {

    public CPFDoesNotContainOnlyDigitsException() {
        super("CPF contains not valid digits. Be sure that CPF is not formatted for CPF digits validation");
    }
}

