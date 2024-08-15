package com.ecom.userservice.controllerAdvice;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String message)
    {
        super(message);
    }

}
