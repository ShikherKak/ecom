package com.ecom.userservice.controllerAdvice;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message)
    {
        super(message);
    }


}
