package com.omar26begue.demo.application.usecase;

import com.omar26begue.demo.application.command.RoomCreatedCommand;
import com.omar26begue.demo.domain.entities.RoomDomain;
import com.omar26begue.demo.domain.exceptions.ExBadRequestException;
import com.omar26begue.demo.domain.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.omar26begue.demo.domain.exceptions.ErrorCode.VALIDATION_ERROR;

@Slf4j
@Component
@AllArgsConstructor
public class RoomCreatedUseCase implements RoomCreatedCommand {

    private final RoomRepository repository;

    @Override
    public RoomDomain execute(RoomDomain payload) {
        log.info("Ejecutando servicio de creación de sala: {}", payload.getName());

        if (repository.findName(payload.getName()) != null) {
            log.warn("La sala {} ya existe", payload.getName());
            throw new ExBadRequestException(VALIDATION_ERROR, "La sala ya existe");
        }

        payload = payload.toBuilder()
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        var response = repository.save(payload);
        log.info("Sala creada con éxito: {}", response.getId());

        return response;
    }
}
