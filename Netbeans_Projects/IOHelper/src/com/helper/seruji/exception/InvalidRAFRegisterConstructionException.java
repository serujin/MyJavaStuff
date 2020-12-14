package com.helper.seruji.exception;

public class InvalidRAFRegisterConstructionException extends Exception {
    public InvalidRAFRegisterConstructionException() {
        super("The length of the String descriptions array and the RafDataType array must be the same, and cannot be null");
    }
}
