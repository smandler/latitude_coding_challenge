package com.latitude.genoapay.codingchallenge.errors;

public class LessThanTwoPricesException extends RuntimeException {
    public LessThanTwoPricesException(String message) {
        super(message);
    }
}
