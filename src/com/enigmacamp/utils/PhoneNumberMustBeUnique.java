package com.enigmacamp.utils;

public class PhoneNumberMustBeUnique extends RuntimeException {
    public PhoneNumberMustBeUnique(String message) {
        super(message);
    }
}
