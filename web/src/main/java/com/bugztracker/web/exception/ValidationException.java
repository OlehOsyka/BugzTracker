package com.bugztracker.web.exception;

/**
 * Created by Y. Vovk
 * Date: 02.10.15
 * Time: 17:47
 */
public class ValidationException extends RuntimeException {

    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

}
