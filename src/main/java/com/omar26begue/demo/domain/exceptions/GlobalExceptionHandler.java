package com.omar26begue.demo.domain.exceptions;

import com.omar26begue.demo.infrastructure.rest.model.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<RestResponse> handle(Throwable throwable) {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), throwable);
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, throwable, ErrorCode.INTERNAL_SERVER_ERROR, throwable.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestResponse> handle(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .orElse(ErrorCode.VALIDATION_ERROR.getText());
        log.error(HttpStatus.BAD_REQUEST.getReasonPhrase(), e);
        var response = RestResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ErrorCode.VALIDATION_ERROR.getCode())
                .message(message)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<RestResponse> handle(HttpMessageNotReadableException e) {
        log.error(HttpStatus.BAD_REQUEST.getReasonPhrase(), e);
        var response = RestResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ErrorCode.VALIDATION_ERROR.getCode())
                .message("Formato de datos inválido. Verifique los tipos y formatos enviados.")
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExBadRequestException.class)
    public ResponseEntity<RestResponse> handle(ExBadRequestException e) {
        log.error(HttpStatus.BAD_REQUEST.getReasonPhrase(), e);
        return buildResponseError(HttpStatus.BAD_REQUEST, e, e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(ExTooManyException.class)
    public ResponseEntity<RestResponse> handle(ExTooManyException e) {
        log.error(HttpStatus.TOO_MANY_REQUESTS.getReasonPhrase(), e);
        return buildResponseError(HttpStatus.TOO_MANY_REQUESTS, e, e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(ExUnauthorizedException.class)
    public ResponseEntity<RestResponse> handle(ExUnauthorizedException e) {
        log.error(HttpStatus.FORBIDDEN.getReasonPhrase(), e);
        return buildResponseError(HttpStatus.FORBIDDEN, e, e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(ExInternalException.class)
    public ResponseEntity<RestResponse> handle(ExInternalException e) {
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);
        return buildResponseError(HttpStatus.INTERNAL_SERVER_ERROR, e, e.getErrorCode(), e.getMessage());
    }

    private ResponseEntity<RestResponse> buildResponseError(HttpStatus httpStatus, Throwable throwable, ErrorCode errorCode, String details) {
        var response = RestResponse.builder()
                .status(httpStatus.value())
                .code(errorCode.getCode())
                .message(details != null ? details : errorCode.getText())
                .details(throwable.getMessage())
                .build();
        return new ResponseEntity<>(response, httpStatus);
    }
}
