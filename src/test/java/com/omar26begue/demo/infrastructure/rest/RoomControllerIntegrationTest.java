package com.omar26begue.demo.infrastructure.rest;

import com.omar26begue.demo.application.command.RoomCreatedCommand;
import com.omar26begue.demo.application.query.RoomListQuery;
import com.omar26begue.demo.domain.entities.RoomDomain;
import com.omar26begue.demo.domain.mappers.RoomMapper;
import com.omar26begue.demo.infrastructure.rest.model.RoomResquest;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("RoomController Tests")
class RoomControllerIntegrationTest {

    @Mock
    private RoomMapper roomMapper;

    @Mock
    private RoomListQuery roomListQuery;

    @Mock
    private RoomCreatedCommand roomCreateCommand;

    private RoomController roomController;

    @BeforeEach
    void setup() {
        roomController = new RoomController(roomMapper, roomListQuery, roomCreateCommand);
    }

    @Test
    @DisplayName("Should list rooms successfully")
    void testGetRoomsPublic() {
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

        List<RoomDomain> rooms = Arrays.asList(room1, room2);
        when(roomListQuery.execute()).thenReturn(rooms);

        roomController.list();

        verify(roomListQuery).execute();
    }

    @Test
    @DisplayName("Should create room successfully")
    void testCreateRoomWithAuth() {
        UUID roomId = UUID.randomUUID();
        RoomResquest request = new RoomResquest();
        request.setName("Sala de Prueba");
        request.setDescription("Sala creada en test");

        RoomDomain requestDomain = RoomDomain.builder()
                .name("Sala de Prueba")
                .description("Sala creada en test")
                .build();

        RoomDomain createdRoom = RoomDomain.builder()
                .id(roomId)
                .name("Sala de Prueba")
                .description("Sala creada en test")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(roomMapper.toDomain(request)).thenReturn(requestDomain);
        when(roomCreateCommand.execute(requestDomain)).thenReturn(createdRoom);

        roomController.created(request);

        verify(roomMapper).toDomain(request);
        verify(roomCreateCommand).execute(requestDomain);
    }

    @Test
    @DisplayName("Should handle exception when creating room")
    void testCreateRoomException() {
        RoomResquest request = new RoomResquest();
        request.setName("Sala");
        request.setDescription("Test");

        RoomDomain requestDomain = RoomDomain.builder()
                .name("Sala")
                .description("Test")
                .build();

        when(roomMapper.toDomain(request)).thenReturn(requestDomain);
        when(roomCreateCommand.execute(any())).thenThrow(new RuntimeException("Error creating room"));

        assertThrows(RuntimeException.class, () -> roomController.created(request));
        verify(roomMapper).toDomain(request);
        verify(roomCreateCommand).execute(requestDomain);
    }
}