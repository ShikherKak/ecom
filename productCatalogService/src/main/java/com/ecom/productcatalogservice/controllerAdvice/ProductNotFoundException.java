package com.ecom.productcatalogservice.controllerAdvice;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(String message)
    {
        super(message);
    }

}
