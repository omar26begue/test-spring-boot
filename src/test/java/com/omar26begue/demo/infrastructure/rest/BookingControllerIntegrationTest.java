package com.omar26begue.demo.infrastructure.rest;

import com.omar26begue.demo.application.command.BookingCancelCommand;
import com.omar26begue.demo.application.command.BookingCreatedCommand;
import com.omar26begue.demo.application.query.BookingPaginatedQuery;
import com.omar26begue.demo.domain.entities.BookingDomain;
import com.omar26begue.demo.domain.entities.RoomDomain;
import com.omar26begue.demo.domain.mappers.BookingMapper;
import com.omar26begue.demo.infrastructure.rest.model.BookingRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookingController Tests")
class BookingControllerIntegrationTest {

    @Mock
    private BookingMapper bookingMapper;

    @Mock
    private BookingCreatedCommand bookingCreatedCommand;

    @Mock
    private BookingPaginatedQuery bookingPaginatedQuery;

    @Mock
    private BookingCancelCommand bookingCancelCommand;

    private BookingController bookingController;

    @BeforeEach
    void setup() {
        bookingController = new BookingController(bookingMapper, bookingCancelCommand,
                bookingPaginatedQuery, bookingCreatedCommand);
    }

    @Test
    @DisplayName("Should list bookings paginated successfully")
    void testGetBookingsPublic() {
        UUID roomId = UUID.randomUUID();
        UUID bookingId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        RoomDomain room = RoomDomain.builder()
                .id(roomId)
                .name("Sala A")
                .build();

        BookingDomain booking = BookingDomain.builder()
                .id(bookingId)
                .room(room)
                .responsable("Test User")
                .dateStart(now.plusDays(1))
                .dateEnd(now.plusDays(1).plusHours(2))
                .cancelled(false)
                .createdAt(now)
                .build();

        List<BookingDomain> bookings = Arrays.asList(booking);
        Page<BookingDomain> page = new PageImpl<>(bookings);
        when(bookingPaginatedQuery.execute(any(), any(), any(), any())).thenReturn(page);

        bookingController.paginated(0, 25, null, null);

        verify(bookingPaginatedQuery).execute(any(), any(), any(), any());
    }

    @Test
    @DisplayName("Should create booking successfully")
    void testCreateBookingSuccess() {
        UUID roomId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        BookingRequest request = new BookingRequest();
        request.setResponsable("Test Usuario");
        request.setDateStart(now.plusDays(5));
        request.setDateEnd(now.plusDays(5).plusHours(2));

        BookingDomain bookingDomain = BookingDomain.builder()
                .responsable("Test Usuario")
                .dateStart(request.getDateStart())
                .dateEnd(request.getDateEnd())
                .build();

        when(bookingMapper.toDomain(request)).thenReturn(bookingDomain);

        bookingController.created(roomId, request);

        verify(bookingMapper).toDomain(request);
        verify(bookingCreatedCommand).execute(any(), any());
    }

    @Test
    @DisplayName("Should throw exception when creating booking with invalid data")
    void testCreateBookingInvalidDateRange() {
        UUID roomId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        BookingRequest request = new BookingRequest();
        request.setResponsable("Test Usuario");
        request.setDateStart(now.plusDays(5).plusHours(2));
        request.setDateEnd(now.plusDays(5)); // End before start

        BookingDomain bookingDomain = BookingDomain.builder()
                .responsable("Test Usuario")
                .dateStart(request.getDateStart())
                .dateEnd(request.getDateEnd())
                .build();

        when(bookingMapper.toDomain(request)).thenReturn(bookingDomain);
        doThrow(new RuntimeException("Invalid date range"))
                .when(bookingCreatedCommand).execute(any(), any());

        assertThrows(RuntimeException.class, () -> bookingController.created(roomId, request));
        verify(bookingMapper).toDomain(request);
    }

    @Test
    @DisplayName("Should handle booking cancellation")
    void testDeleteBookingWithAuth() {
        UUID bookingId = UUID.randomUUID();

        bookingController.cancel(bookingId);

        verify(bookingCancelCommand).execute(bookingId);
    }
}