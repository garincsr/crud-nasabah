package com.crud.utils;

public class IDMustBeUnique extends RuntimeException {
    public IDMustBeUnique(String message) {
        super(message);
    }
}
