package com.prathamesh.AirBnb.repositories;

import com.prathamesh.AirBnb.entities.InventoryEntity;
import com.prathamesh.AirBnb.entities.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {

    List<InventoryEntity> findByRoomOrderByDate(RoomEntity room);
}
