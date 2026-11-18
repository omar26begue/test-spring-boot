package com.omar26begue.demo.application.command;

import java.util.UUID;

public interface BookingCancelCommand {

    void execute(UUID bookingId);
}
