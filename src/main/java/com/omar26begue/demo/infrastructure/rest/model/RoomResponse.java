package com.omar26begue.demo.infrastructure.rest.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "createdAt",
        "updatedAt"
})
public class RoomResponse extends RoomResquest {

    UUID id;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
