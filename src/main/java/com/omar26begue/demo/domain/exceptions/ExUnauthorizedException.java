package com.omar26begue.demo.domain.exceptions;

public class ExUnauthorizedException extends GenericException {

    public ExUnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
