package com.omar26begue.demo.application.query;

import com.omar26begue.demo.domain.entities.RoomDomain;

import java.util.List;

public interface RoomListQuery {

    List<RoomDomain> execute();
}
