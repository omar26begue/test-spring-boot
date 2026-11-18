package com.omar26begue.demo.domain.repositories;

import com.omar26begue.demo.domain.entities.RoomDomain;

import java.util.List;

public interface RoomRepository {

    RoomDomain findName(String name);

    List<RoomDomain> list();

    RoomDomain save(RoomDomain payload);
}
