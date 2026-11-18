package com.omar26begue.demo.application.usecase;

import com.omar26begue.demo.application.query.BookingPaginatedQuery;
import com.omar26begue.demo.domain.entities.BookingDomain;
import com.omar26begue.demo.domain.repositories.BookingRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class BookingPaginatedUseCase implements BookingPaginatedQuery {

    private final BookingRepository repository;

    @Override
    public Page<BookingDomain> execute(Integer page, Integer size, UUID idBooking, LocalDate date) {
        log.info("Ejecutando servicio de listado de reservas: page={}, size={}", page, size);
        var response = repository.paginated(page, size, idBooking, date);
        log.info("Cantidad de reservas: {}", response.getTotalElements());
        return response;
    }
}
