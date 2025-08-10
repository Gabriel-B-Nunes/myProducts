package com.example.myProducts.exceptions;

public class GenericException extends RuntimeException {

    public GenericException() {
        super("Internal server error");
    }

}