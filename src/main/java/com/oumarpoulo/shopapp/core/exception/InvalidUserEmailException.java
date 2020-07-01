package com.oumarpoulo.shopapp.core.exception;

public class InvalidUserEmailException extends TechnicalException{
    public InvalidUserEmailException(String message) {
        super(message);
    }
}
