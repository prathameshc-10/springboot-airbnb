package com.prathamesh.AirBnb.service;

import com.prathamesh.AirBnb.dto.InventoryDTO;
import com.prathamesh.AirBnb.dto.UpdateInventoryRequestDTO;
import com.prathamesh.AirBnb.entities.RoomEntity;
import com.prathamesh.AirBnb.exceptions.ResourceNotFoundException;
import com.prathamesh.AirBnb.repositories.InventoryRepository;
import com.prathamesh.AirBnb.repositories.RoomRepository;
import com.prathamesh.AirBnb.service.interfaces.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<InventoryDTO> getAllInventoryByRoom(Long roomId) {
        log.info("Getting all inventory by room for roomId: {}", roomId);
        RoomEntity room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with Id: " + roomId));

        return inventoryRepository
                .findByRoomOrderByDate(room)
                .stream()
                .map((element) -> modelMapper.map(element, InventoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateInventory(Long roomId, UpdateInventoryRequestDTO updateInventoryRequestDTO) {
        
    }
}
