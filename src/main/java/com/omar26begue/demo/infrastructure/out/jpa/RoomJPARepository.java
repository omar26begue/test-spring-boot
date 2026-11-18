package com.omar26begue.demo.infrastructure.out.jpa;

import com.omar26begue.demo.infrastructure.out.jpa.model.RoomDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomJPARepository extends JpaRepository<RoomDAO, UUID> {

}
