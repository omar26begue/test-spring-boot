package com.omar26begue.demo.domain.entities;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder(toBuilder = true)
public class BookingDomain {

    UUID id;
    RoomDomain room;
    String responsable;
    LocalDateTime dateStart;
    LocalDateTime dateEnd;
    Boolean cancelled;
    LocalDateTime createdAt;
}
