package com.javarush.stepanov.exception;

import static com.javarush.stepanov.constants.ConstantsCommon.ERROR_MESSAGE;

@SuppressWarnings("unused")
public class AppException extends RuntimeException {

    public AppException() { super(ERROR_MESSAGE); }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppException(Throwable cause) {
        super(cause);
    }
}