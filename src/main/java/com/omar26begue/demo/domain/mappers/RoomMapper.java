package com.omar26begue.demo.domain.mappers;

import com.omar26begue.demo.domain.entities.RoomDomain;
import com.omar26begue.demo.infrastructure.jpa.model.RoomDAO;
import com.omar26begue.demo.infrastructure.rest.model.RoomResponse;
import com.omar26begue.demo.infrastructure.rest.model.RoomResquest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    RoomDomain toDomain(RoomDAO entity);

    RoomDomain toDomain(RoomResquest request);

    RoomResponse toResponse(RoomDomain domain);

    RoomDAO toEntity(RoomDomain domain);
}
