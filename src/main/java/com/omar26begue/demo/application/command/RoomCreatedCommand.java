package com.omar26begue.demo.application.command;

import com.omar26begue.demo.domain.entities.RoomDomain;

public interface RoomCreatedCommand {

    RoomDomain execute(RoomDomain payload);
}
