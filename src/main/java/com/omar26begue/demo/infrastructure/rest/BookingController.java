package com.omar26begue.demo.infrastructure.rest;

import com.omar26begue.demo.application.command.BookingCreatedCommand;
import com.omar26begue.demo.domain.mappers.BookingMapper;
import com.omar26begue.demo.infrastructure.rest.model.BookingRequest;
import com.omar26begue.demo.infrastructure.rest.model.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/bookings")
@AllArgsConstructor
@Tag(name = "Reservas", description = "Gestión de reservas de salas.")
public class BookingController {

    private final BookingMapper mapper;
    private final BookingCreatedCommand bookingCreatedCommand;

    @Operation(
            summary = "Creación de reservas",
            description = "Crea una nueva reserva."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reserva creada correctamente.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))
            }),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida.", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestResponse.class))
            }),
            @ApiResponse(responseCode = "default", description = "Error inesperado.", content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RestResponse.class))
            })
    })
    @PostMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> created(
            @PathVariable UUID id,
            @Valid @RequestBody BookingRequest payload
    ) {
        bookingCreatedCommand.execute(id, mapper.toDomain(payload));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
