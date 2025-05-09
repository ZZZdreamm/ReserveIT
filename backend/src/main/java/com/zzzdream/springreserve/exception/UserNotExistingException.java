package com.zzzdream.springreserve.exception;

import org.springframework.security.core.AuthenticationException;

public class UserNotExistingException extends AuthenticationException {
    public UserNotExistingException(String msg, Throwable cause) {
        super(msg, cause);
    }
    public UserNotExistingException(String fieldName, String fieldValue) {
        super(String.format("User with %s = %s not found", fieldName, fieldValue));
    }
}
