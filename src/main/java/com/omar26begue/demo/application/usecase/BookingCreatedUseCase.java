package com.omar26begue.demo.application.usecase;

import com.omar26begue.demo.application.command.BookingCreatedCommand;
import com.omar26begue.demo.domain.entities.BookingDomain;
import com.omar26begue.demo.domain.exceptions.ExBadRequestException;
import com.omar26begue.demo.domain.exceptions.ExNotFoundException;
import com.omar26begue.demo.domain.exceptions.ExTooManyException;
import com.omar26begue.demo.domain.repositories.BookingRepository;
import com.omar26begue.demo.domain.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.omar26begue.demo.domain.exceptions.ErrorCode.NOT_FOUND;
import static com.omar26begue.demo.domain.exceptions.ErrorCode.NO_AVAILABILITY;
import static com.omar26begue.demo.domain.exceptions.ErrorCode.VALIDATION_ERROR;

@Slf4j
@Component
@AllArgsConstructor
public class BookingCreatedUseCase implements BookingCreatedCommand {

    private final BookingRepository repository;
    private final RoomRepository roomRepository;

    @Override
    public void execute(UUID idRoom, BookingDomain payload) {
        log.info("Ejecutando servicio de creacion de reserva: {}", idRoom);
        var roomData = roomRepository.findId(idRoom);
        if (roomData == null) {
            log.warn("La informacion de la sala {} no se encuentra disponible", idRoom);
            throw new ExNotFoundException(NOT_FOUND);
        }

        validateDateRange(payload.getDateStart(), payload.getDateEnd());
        verifyRoomAvailability(idRoom, payload.getDateStart(), payload.getDateEnd());

        payload = payload.toBuilder()
                .room(roomData)
                .cancelled(false)
                .createdAt(LocalDateTime.now())
                .build();
        var response = repository.save(payload);
        log.info("Reserva creada con exito: {}", response.getId());
    }

    private void validateDateRange(LocalDateTime dateStart, LocalDateTime dateEnd) {
        if (dateEnd == null || dateStart == null) {
            log.warn("Las fechas de inicio y fin son requeridas");
            throw new ExBadRequestException(VALIDATION_ERROR, "Las fechas de inicio y fin son requeridas");
        }
        if (!dateEnd.isAfter(dateStart)) {
            log.warn("La fecha de fin debe ser mayor que la fecha de inicio");
            throw new ExBadRequestException(VALIDATION_ERROR, "La fecha de fin debe ser mayor que la fecha de inicio");
        }
    }

    private void verifyRoomAvailability(UUID roomId, LocalDateTime dateStart, LocalDateTime dateEnd) {
        var conflicts = repository.findConflictingBookings(roomId, dateStart, dateEnd);
        if (!conflicts.isEmpty()) {
            log.warn("La sala {} ya tiene reservas en el horario especificado", roomId);
            throw new ExTooManyException(NO_AVAILABILITY, "La sala ya tiene reservas en el horario especificado");
        }
    }
}
