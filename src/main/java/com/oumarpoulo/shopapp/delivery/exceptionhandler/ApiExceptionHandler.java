package com.oumarpoulo.shopapp.delivery.exceptionhandler;

import com.oumarpoulo.shopapp.core.exception.*;
import com.oumarpoulo.shopapp.delivery.api.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(
            {InvalidUserEmailException.class, InvalidUsernameException.class}
    )
    public ResponseEntity<ApiErrorResponse> handleBadRequest(TechnicalException exception) {
        return new ResponseEntity(new ApiErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(
            {UserEmailExistsException.class, UserExistsException.class}
    )
    public ResponseEntity<ApiErrorResponse> handleDataConflict(TechnicalException exception) {
        return new ResponseEntity(new ApiErrorResponse(exception.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleTechnicalException(TechnicalException exception) {
        return new ResponseEntity(new ApiErrorResponse(exception.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> handleException(Exception exception) {
        return new ResponseEntity(new ApiErrorResponse(exception.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
