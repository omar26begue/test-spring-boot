package com.omar26begue.demo.infrastructure.jpa;

import com.omar26begue.demo.infrastructure.jpa.model.BookingDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookingJPARepository extends JpaRepository<BookingDAO, UUID> {

    @Query("""
                SELECT b FROM BookingDAO b WHERE b.room.id = :roomId AND b.cancelled = false AND ((b.dateStart < :dateEnd AND b.dateEnd > :dateStart))
            """)
    List<BookingDAO> findConflictingBookings(
            @Param("roomId") UUID roomId,
            @Param("dateStart") LocalDateTime dateStart,
            @Param("dateEnd") LocalDateTime dateEnd
    );
}
