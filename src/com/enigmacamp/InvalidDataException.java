package com.enigmacamp;

import java.io.IOException;
import java.util.InputMismatchException;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException (String msg) {
        super(msg);
    }
}
