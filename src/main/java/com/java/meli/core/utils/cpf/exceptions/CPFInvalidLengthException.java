package com.java.meli.core.utils.cpf.exceptions;

public class CPFInvalidLengthException extends CPFValidatorException {

    public CPFInvalidLengthException() {
        super("CPF does not have the required number of digits");
    }
}
