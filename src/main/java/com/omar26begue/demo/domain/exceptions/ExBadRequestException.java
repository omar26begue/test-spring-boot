package com.omar26begue.demo.domain.exceptions;

public class ExBadRequestException extends GenericException {

    public ExBadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
