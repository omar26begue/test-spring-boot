package com.omar26begue.demo.domain.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INTERNAL_SERVER_ERROR("0000", "Error interno de la aplicación."),
    VALIDATION_ERROR("0001", "Error de validación de datos."),
    BAD_REQUEST("0002", "Solicitud incorrecta."),
    NOT_FOUND("0003", "Recurso no encontrado."),
    NO_AVAILABILITY("0004", "La sala no está disponible en el horario solicitado."),
    ;

    private final String code;
    private final String text;
}
