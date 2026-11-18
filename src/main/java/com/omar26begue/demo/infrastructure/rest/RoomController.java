package com.omar26begue.demo.infrastructure.rest;

import com.omar26begue.demo.application.command.RoomCreatedCommand;
import com.omar26begue.demo.application.query.RoomListQuery;
import com.omar26begue.demo.domain.mappers.RoomMapper;
import com.omar26begue.demo.infrastructure.rest.model.RestResponse;
import com.omar26begue.demo.infrastructure.rest.model.RoomResponse;
import com.omar26begue.demo.infrastructure.rest.model.RoomResquest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
@Tag(name = "Salas", description = "Gestión de salas")
public class RoomController {

    private final RoomMapper mapper;
    private final RoomListQuery roomListQuery;
    private final RoomCreatedCommand roomCreateCommand;

    @Operation(
            summary = "Listado de salas",
            description = "Retorna una lista de todas las salas disponibles."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de salas obtenido correctamente.", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomResponse.class)))
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
    public ResponseEntity<List<RoomResponse>> list() {
        var response = roomListQuery.execute();
        return new ResponseEntity<>(response.stream().map(mapper::toResponse).toList(), HttpStatus.OK);
    }

    @Operation(
            summary = "Creación de salas",
            description = "Crea una nueva sala."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sala creada correctamente.", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RoomResponse.class)))
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
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<RoomResponse> created(@Valid @RequestBody RoomResquest payload) {
        var response = roomCreateCommand.execute(mapper.toDomain(payload));
        return new ResponseEntity<>(mapper.toResponse(response), HttpStatus.OK);
    }
}
