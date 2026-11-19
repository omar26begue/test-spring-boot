package com.omar26begue.demo.application.usecase;

import com.omar26begue.demo.domain.entities.BookingDomain;
import com.omar26begue.demo.domain.entities.RoomDomain;
import com.omar26begue.demo.domain.exceptions.ExBadRequestException;
import com.omar26begue.demo.domain.repositories.BookingRepository;
import com.omar26begue.demo.domain.repositories.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookingCreatedUseCase Tests")
class BookingCreatedUseCaseTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private RoomRepository roomRepository;

    private BookingCreatedUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new BookingCreatedUseCase(bookingRepository, roomRepository);
    }

    @Test
    @DisplayName("Should create booking successfully")
    void testCreateBookingSuccess() {
        // Arrange
        UUID roomId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = now.plusDays(1);
        LocalDateTime endDate = startDate.plusHours(2);

        RoomDomain room = RoomDomain.builder()
                .id(roomId)
                .name("Sala A")
                .description("Test Room")
                .build();

        BookingDomain bookingRequest = BookingDomain.builder()
                .responsable("Juan Pérez")
                .dateStart(startDate)
                .dateEnd(endDate)
                .build();

        BookingDomain bookingSaved = bookingRequest.toBuilder()
                .room(room)
                .cancelled(false)
                .createdAt(now)
                .build();

        when(roomRepository.findId(roomId)).thenReturn(room);
        when(bookingRepository.findConflictingBookings(roomId, startDate, endDate)).thenReturn(java.util.List.of());
        when(bookingRepository.save(any(BookingDomain.class))).thenReturn(bookingSaved);

        // Act
        useCase.execute(roomId, bookingRequest);

        // Assert
        verify(roomRepository).findId(roomId);
        verify(bookingRepository).findConflictingBookings(roomId, startDate, endDate);
        verify(bookingRepository).save(any(BookingDomain.class));
    }

    @Test
    @DisplayName("Should throw exception when end date is before start date")
    void testCreateBookingInvalidDateRange() {
        // Arrange
        UUID roomId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startDate = now.plusDays(1);
        LocalDateTime endDate = startDate.minusHours(1);

        RoomDomain room = RoomDomain.builder()
                .id(roomId)
                .name("Sala A")
                .build();

        BookingDomain bookingRequest = BookingDomain.builder()
                .responsable("Juan Pérez")
                .dateStart(startDate)
                .dateEnd(endDate)
                .build();

        when(roomRepository.findId(roomId)).thenReturn(room);

        // Act & Assert
        assertThrows(ExBadRequestException.class, () -> useCase.execute(roomId, bookingRequest));
        verify(roomRepository).findId(roomId);
        verify(bookingRepository, never()).save(any());
    }

    @Test
    @DisplayName("Should throw exception when room not found")
    void testCreateBookingRoomNotFound() {
        // Arrange
        UUID roomId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        BookingDomain bookingRequest = BookingDomain.builder()
                .responsable("Juan Pérez")
                .dateStart(now.plusDays(1))
                .dateEnd(now.plusDays(1).plusHours(2))
                .build();

        when(roomRepository.findId(roomId)).thenReturn(null);

        // Act & Assert
        assertThrows(Exception.class, () -> useCase.execute(roomId, bookingRequest));
        verify(roomRepository).findId(roomId);
        verify(bookingRepository, never()).save(any());
    }
}