package com.omar26begue.demo.infrastructure.jpa;

import com.omar26begue.demo.domain.entities.RoomDomain;
import com.omar26begue.demo.domain.mappers.RoomMapper;
import com.omar26begue.demo.domain.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@AllArgsConstructor
public class RoomRepositoryService implements RoomRepository {

    private final RoomMapper mapper;
    private final RoomJPARepository repository;

    @Override
    public RoomDomain findName(String name) {
        var entity = repository.findByName(name);
        return entity.map(mapper::toDomain).orElse(null);
    }

    @Override
    public List<RoomDomain> list() {
        var response = repository.findAll();
        return response.stream().map(mapper::toDomain).toList();
    }

    @Override
    public RoomDomain save(RoomDomain payload) {
        var entity = mapper.toEntity(payload);
        var savedEntity = repository.save(entity);
        return mapper.toDomain(savedEntity);
    }
}
