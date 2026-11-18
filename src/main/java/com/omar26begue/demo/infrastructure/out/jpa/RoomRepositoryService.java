package com.omar26begue.demo.infrastructure.out.jpa;

import com.omar26begue.demo.domain.mappers.RoomMapper;
import com.omar26begue.demo.domain.repositories.RoomRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@AllArgsConstructor
public class RoomRepositoryService implements RoomRepository {

    private final RoomMapper mapper;
    private final RoomJPARepository repository;
}
