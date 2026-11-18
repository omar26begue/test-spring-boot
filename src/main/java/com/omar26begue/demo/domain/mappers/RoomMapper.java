package com.omar26begue.demo.domain.mappers;

import com.omar26begue.demo.domain.entities.RoomDomain;
import com.omar26begue.demo.infrastructure.jpa.model.RoomDAO;
import com.omar26begue.demo.infrastructure.rest.model.RoomResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDomain toDomain(RoomDAO entity);

    RoomResponse toResponse(RoomDomain domain);
}
