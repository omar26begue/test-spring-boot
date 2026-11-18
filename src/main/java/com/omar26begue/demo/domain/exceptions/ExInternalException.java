package com.omar26begue.demo.domain.exceptions;

public class ExInternalException extends GenericException {

    public ExInternalException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
