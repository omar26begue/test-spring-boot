package com.omar26begue.demo.domain.repositories;

import com.omar26begue.demo.domain.entities.BookingDomain;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingRepository {

    BookingDomain findById(UUID bookingId);

    List<BookingDomain> findConflictingBookings(UUID roomId, LocalDateTime dateStart, LocalDateTime dateEnd);

    Page<BookingDomain> paginated(Integer page, Integer size, UUID idBooking);

    BookingDomain save(BookingDomain payload);
}
