package com.omar26begue.demo.domain.exceptions;

public abstract class GenericException extends RuntimeException {

    private static final String COMMA = ",";
    private static final String SPACE = " ";

    private final ErrorCode errorCode;

    public GenericException(ErrorCode errorCode) {
        super(errorCode.getText());
        this.errorCode = errorCode;
    }

    public GenericException(ErrorCode errorCode, String message) {
        super(buildMessage(message, errorCode));
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    private static String buildMessage(String message, ErrorCode errorCode) {
        return message;
    }

    public GenericException(ErrorCode errorCode, String message, Throwable cause) {
        super(buildMessage(message, errorCode), cause);
        this.errorCode = errorCode;
    }
}
