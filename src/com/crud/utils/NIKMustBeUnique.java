package com.crud.utils;

public class NIKMustBeUnique extends RuntimeException {
    public NIKMustBeUnique(String message) {
        super(message);
    }
}
