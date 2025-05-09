package com.zzzdream.springreserve.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotVerifiedException extends AuthenticationException {
    public UserNotVerifiedException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserNotVerifiedException(String fieldName, String fieldValue) {
        super(String.format("User with %s = %s is not verified", fieldName, fieldValue));
    }
}
