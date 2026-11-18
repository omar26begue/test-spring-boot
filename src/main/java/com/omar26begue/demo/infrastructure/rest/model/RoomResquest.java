package com.omar26begue.demo.infrastructure.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "name",
        "description"
})
public class RoomResquest {

    @JsonProperty("name")
    @Schema(name = "name", description = "Nombre de la sala", example = "Sala 1")
    String name;

    @JsonProperty("description")
    @Schema(name = "description", description = "Descripción de la sala", example = "Sala de reuniones")
    String description;
}
