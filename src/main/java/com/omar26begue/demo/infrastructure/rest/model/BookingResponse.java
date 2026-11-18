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
        "responsable",
        "date_start",
        "date_end",
        "created_at"})
public class BookingResponse extends BookingRequest {

    UUID id;
    LocalDateTime createdAt;
}
