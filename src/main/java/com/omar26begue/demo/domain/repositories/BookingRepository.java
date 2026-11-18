package com.omar26begue.demo.domain.repositories;

import com.omar26begue.demo.domain.entities.BookingDomain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository {

    BookingDomain save(BookingDomain payload);

    List<BookingDomain> findConflictingBookings(UUID roomId, LocalDateTime dateStart, LocalDateTime dateEnd);
}
