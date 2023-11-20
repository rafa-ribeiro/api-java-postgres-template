package com.java.meli.core.utils.cpf.exceptions;

public class EmptyCPFException extends CPFValidatorException {

    public EmptyCPFException() {
        super("The informed CPF is empty or null");
    }
}
