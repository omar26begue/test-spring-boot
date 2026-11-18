package com.omar26begue.demo.infrastructure.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "responsable",
        "date_start",
        "date_end"})
public class BookingRequest {

    @JsonProperty("responsable")
    @NotBlank(message = "El nombre del responsable es obligatorio.")
    @Schema(description = "Nombre de la persona responsable de la reserva.", example = "Juan Pérez")
    private String responsable;

    @JsonProperty("date_start")
    @NotNull(message = "La fecha de inicio de la reserva es obligatoria.")
    @Schema(description = "Fecha de inicio de la reserva.", example = "2023-10-10T10:00:00")
    private LocalDateTime dateStart;

    @JsonProperty("date_end")
    @NotNull(message = "La fecha de fin de la reserva es obligatoria.")
    @Schema(description = "Fecha de fin de la reserva.", example = "2023-10-10T12:00:00")
    private LocalDateTime dateEnd;
}
