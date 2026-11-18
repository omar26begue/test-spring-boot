package com.omar26begue.demo.domain.exceptions;

public class ExAccessException extends GenericException {

    public ExAccessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
