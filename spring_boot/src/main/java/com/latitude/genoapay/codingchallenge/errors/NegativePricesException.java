package com.latitude.genoapay.codingchallenge.errors;

public class NegativePricesException extends RuntimeException{
    public NegativePricesException(String message) {
        super(message);
    }
}
