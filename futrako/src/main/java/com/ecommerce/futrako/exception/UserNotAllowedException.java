package com.ecommerce.futrako.exception;

public class UserNotAllowedException extends RuntimeException{
    public UserNotAllowedException() {
        super();
    }

    public UserNotAllowedException(final String message) {
        super(message);
    }

    public UserNotAllowedException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
