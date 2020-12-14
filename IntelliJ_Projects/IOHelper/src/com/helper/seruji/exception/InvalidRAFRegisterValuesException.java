package com.helper.seruji.exception;

public class InvalidRAFRegisterValuesException extends Exception {
    public InvalidRAFRegisterValuesException() {
        super("The length of the Object array to introduce in the file must have the same length as the file register structure, and cannot be null");
    }
}
