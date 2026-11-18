package com.omar26begue.demo.domain.repositories;

import com.omar26begue.demo.domain.entities.RoomDomain;

import java.util.List;
import java.util.UUID;

public interface RoomRepository {

    RoomDomain findId(UUID id);

    RoomDomain findName(String name);

    List<RoomDomain> list();

    RoomDomain save(RoomDomain payload);
}
