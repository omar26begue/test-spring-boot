package com.omar26begue.demo.application.command;

import com.omar26begue.demo.domain.entities.BookingDomain;

import java.util.UUID;

public interface BookingCreatedCommand {

    void execute(UUID idRoom, BookingDomain payload);
}
