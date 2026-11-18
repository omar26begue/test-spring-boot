package com.omar26begue.demo.domain.exceptions;

public class ExTooManyException extends GenericException {

    public ExTooManyException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
