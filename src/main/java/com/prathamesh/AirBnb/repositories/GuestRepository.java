package com.prathamesh.AirBnb.repositories;

import com.prathamesh.AirBnb.dto.GuestDTO;
import com.prathamesh.AirBnb.entities.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity, Long> {
    List<GuestDTO> findByUser(UserEntity user);
}