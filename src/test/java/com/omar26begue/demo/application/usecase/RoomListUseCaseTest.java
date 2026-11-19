package com.omar26begue.demo.application.usecase;

import com.omar26begue.demo.domain.entities.RoomDomain;
import com.omar26begue.demo.domain.repositories.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("RoomListUseCase Tests")
class RoomListUseCaseTest {

    @Mock
    private RoomRepository roomRepository;

    private RoomListUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new RoomListUseCase(roomRepository);
    }

    @Test
    @DisplayName("Should return list of rooms successfully")
    void testListRoomsSuccess() {
        // Arrange
        UUID roomId1 = UUID.randomUUID();
        UUID roomId2 = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        RoomDomain room1 = RoomDomain.builder()
                .id(roomId1)
                .name("Sala A")
                .description("Descripción A")
                .createdAt(now)
                .updatedAt(now)
                .build();

        RoomDomain room2 = RoomDomain.builder()
                .id(roomId2)
                .name("Sala B")
                .description("Descripción B")
                .createdAt(now)
                .updatedAt(now)
                .build();

        List<RoomDomain> expectedRooms = Arrays.asList(room1, room2);
        when(roomRepository.list()).thenReturn(expectedRooms);

        // Act
        List<RoomDomain> result = useCase.execute();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Sala A", result.get(0).getName());
        assertEquals("Sala B", result.get(1).getName());
        verify(roomRepository).list();
    }

    @Test
    @DisplayName("Should return empty list when no rooms exist")
    void testListRoomsEmpty() {
        // Arrange
        when(roomRepository.list()).thenReturn(List.of());

        // Act
        List<RoomDomain> result = useCase.execute();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(roomRepository).list();
    }

    @Test
    @DisplayName("Should handle exception from repository")
    void testListRoomsException() {
        // Arrange
        when(roomRepository.list()).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> useCase.execute());
        verify(roomRepository).list();
    }
}