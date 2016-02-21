package com.bugztracker.web.exception;

/**
 * Created by Y. Vovk
 * Date: 02.10.15
 * Time: 17:47
 */
public class EncodingException extends RuntimeException {

    public EncodingException() {
        super();
    }

    public EncodingException(String message) {
        super(message);
    }

    public EncodingException(String message, Throwable cause) {
        super(message, cause);
    }

}
