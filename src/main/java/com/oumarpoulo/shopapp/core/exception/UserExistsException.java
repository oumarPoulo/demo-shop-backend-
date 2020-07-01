package com.oumarpoulo.shopapp.core.exception;

public class UserExistsException extends TechnicalException{
    public UserExistsException(String message) {
        super(message);
    }
}
