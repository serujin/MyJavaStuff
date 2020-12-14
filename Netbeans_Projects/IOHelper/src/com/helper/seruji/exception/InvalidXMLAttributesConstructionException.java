package com.helper.seruji.exception;

public class InvalidXMLAttributesConstructionException extends Exception {
    public InvalidXMLAttributesConstructionException() {
        super("The length of the String name array and the values array must be the same, and cannot be null");
    }
}
