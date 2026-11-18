package com.omar26begue.demo.domain.exceptions;

public class ExNotFoundException extends GenericException {

    public ExNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

    public ExNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public ExNotFoundException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode, errorCode.getText(), throwable);
    }

    public ExNotFoundException(ErrorCode errorCode, String message, Throwable throwable) {
        super(errorCode, message, throwable);
    }
}
