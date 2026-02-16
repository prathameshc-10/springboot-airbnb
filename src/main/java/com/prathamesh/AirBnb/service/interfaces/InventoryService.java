package com.prathamesh.AirBnb.service.interfaces;

import com.prathamesh.AirBnb.dto.InventoryDTO;
import com.prathamesh.AirBnb.dto.UpdateInventoryRequestDTO;

import java.util.List;

public interface InventoryService {
    List<InventoryDTO> getAllInventoryByRoom(Long roomId);
    void updateInventory(Long roomId, UpdateInventoryRequestDTO updateInventoryRequestDTO);
}
