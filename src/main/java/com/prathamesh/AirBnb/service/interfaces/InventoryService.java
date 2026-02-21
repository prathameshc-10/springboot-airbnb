package com.prathamesh.AirBnb.service.interfaces;

import com.prathamesh.AirBnb.dto.InventoryDTO;
import com.prathamesh.AirBnb.dto.UpdateInventoryRequestDTO;
import com.prathamesh.AirBnb.entities.RoomEntity;

import java.util.List;

public interface InventoryService {
    void initializeRoomForAYear(RoomEntity room);

    void deleteAllInventories(RoomEntity room);

    List<InventoryDTO> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDTO updateInventoryRequestDTO);
}
