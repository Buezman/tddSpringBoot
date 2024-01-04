package com.buezman.testdemo.config;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final String status;
    private final String message;


    /**
     * Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message  the detail message. The detail message is saved for
     *                 later retrieval by the {@link #getMessage()} method.
     */
    public CustomException(String status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
