package com.example.myProducts.exceptions;

public class GenericException extends RuntimeException {

    //constructors
    public GenericException() {
        super("Internal server error");
    }

}