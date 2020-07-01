package com.oumarpoulo.shopapp.core.exception;

public class UserEmailExistsException extends TechnicalException{
    public UserEmailExistsException(String message) {
        super(message);
    }
}
