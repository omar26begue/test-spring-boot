package com.omar26begue.demo.application.usecase;

import com.omar26begue.demo.application.query.RoomListQuery;
import com.omar26begue.demo.domain.entities.RoomDomain;
import com.omar26begue.demo.domain.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class RoomListUseCase implements RoomListQuery {

    private final RoomRepository repository;

    @Override
    public List<RoomDomain> execute() {
        log.info("Ejecutando servicio de listado de salas");
        return repository.list();
    }
}
