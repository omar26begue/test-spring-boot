package com.omar26begue.demo.domain.mappers;

import com.omar26begue.demo.domain.entities.BookingDomain;
import com.omar26begue.demo.infrastructure.jpa.model.BookingDAO;
import com.omar26begue.demo.infrastructure.rest.model.BookingRequest;
import com.omar26begue.demo.infrastructure.rest.model.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    BookingDAO toEntity(BookingDomain domain);

    BookingDomain toDomain(BookingDAO entity);

    BookingDomain toDomain(BookingRequest request);

    BookingResponse toResponse(BookingDomain domain);
}
