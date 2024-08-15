package com.ecom.userservice.controllerAdvice;

public class EmailAlreadyInUseException extends RuntimeException{

    public EmailAlreadyInUseException(String message)
    {
        super(message);
    }


}
