package com.omar26begue.demo.application.usecase;

import com.omar26begue.demo.application.command.BookingCancelCommand;
import com.omar26begue.demo.domain.exceptions.ExNotFoundException;
import com.omar26begue.demo.domain.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.omar26begue.demo.domain.exceptions.ErrorCode.NOT_FOUND;

@Slf4j
@Component
@AllArgsConstructor
public class BookingCancelUseCase implements BookingCancelCommand {

    private final BookingRepository repository;

    @Override
    public void execute(UUID bookingId) {
        log.info("Ejecutando servicio de cancelación de reserva con ID: {}", bookingId);
        var dataBooking = repository.findById(bookingId);
        if (dataBooking == null) {
            log.warn("No se encontró la reserva con ID: {}", bookingId);
            throw new ExNotFoundException(NOT_FOUND, "No se encontró la reserva con ID: " + bookingId);
        }

        dataBooking = dataBooking.toBuilder()
                .cancelled(true)
                .build();
        repository.save(dataBooking);
        log.info("Reserva con ID: {} cancelada exitosamente.", bookingId);
    }
}
