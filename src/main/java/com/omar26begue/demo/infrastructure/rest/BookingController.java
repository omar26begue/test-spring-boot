package com.omar26begue.demo.infrastructure.rest;

import com.omar26begue.demo.application.command.BookingCancelCommand;
import com.omar26begue.demo.application.command.BookingCreatedCommand;
import com.omar26begue.demo.application.query.BookingPaginatedQuery;
import com.omar26begue.demo.domain.mappers.BookingMapper;
import com.omar26begue.demo.infrastructure.rest.model.BookingRequest;
import com.omar26begue.demo.infrastructure.rest.model.BookingResponse;
import com.omar26begue.demo.infrastructure.rest.model.RestPaginatedResponse;
import com.omar26begue.demo.infrastructure.rest.model.RestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private final BookingCancelCommand bookingCancelUseCase;
    private final BookingPaginatedQuery bookingPaginatedQuery;
    private final BookingCreatedCommand bookingCreatedCommand;

    @Operation(
            summary = "Listado de reservas",
            description = "Obtiene una lista paginada de reservas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas obtenidas correctamente.", content = {
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
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<RestPaginatedResponse<BookingResponse>> paginated(
            @Parameter(description = "Número de página (base 0).", required = true, example = "1")
            @RequestParam Integer page,
            @Parameter(description = "Cantidad de elementos por página.", required = true, example = "25")
            @RequestParam(defaultValue = "25") Integer size,

            @Parameter(description = "Identificador de la sala", example = "123e4567-e89b-12d3-a456-426614174000")
            @RequestParam(required = false) UUID idBooking
    ) {
        var response = bookingPaginatedQuery.execute(page, size, idBooking);
        return new ResponseEntity<>(RestPaginatedResponse.<BookingResponse>builder()
                .page(response.getNumber())
                .size(response.getSize())
                .totalElement(response.getTotalElements())
                .totalPages(response.getTotalPages())
                .data(response.getContent().stream().map(mapper::toResponse).toList())
                .build(), HttpStatus.OK);
    }

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

    @Operation(
            summary = "Cancelación de reservas",
            description = "Cancela una reserva existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Reserva cancelada correctamente.", content = {
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
    @PatchMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancel(@PathVariable UUID id) {
        bookingCancelUseCase.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
