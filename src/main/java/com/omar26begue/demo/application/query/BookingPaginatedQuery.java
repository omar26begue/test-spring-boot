package com.omar26begue.demo.application.query;

import com.omar26begue.demo.domain.entities.BookingDomain;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BookingPaginatedQuery {

    Page<BookingDomain> execute(Integer page, Integer size, UUID idBooking);
}
