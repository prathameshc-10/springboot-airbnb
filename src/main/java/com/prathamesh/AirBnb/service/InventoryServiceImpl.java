package com.prathamesh.AirBnb.service;

import com.prathamesh.AirBnb.dto.InventoryDTO;
import com.prathamesh.AirBnb.dto.UpdateInventoryRequestDTO;
import com.prathamesh.AirBnb.entities.InventoryEntity;
import com.prathamesh.AirBnb.entities.RoomEntity;
import com.prathamesh.AirBnb.exceptions.ResourceNotFoundException;
import com.prathamesh.AirBnb.repositories.InventoryRepository;
import com.prathamesh.AirBnb.repositories.RoomRepository;
import com.prathamesh.AirBnb.service.interfaces.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    public void initializeRoomForAYear(RoomEntity room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for(; !today.isAfter(endDate); today = today.plusDays(1)){
            InventoryEntity inventory = InventoryEntity.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookCount(0)
                    .reservedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteAllInventories(RoomEntity room) {
        log.info("Delete all inventories of room with ID: {}", room.getId());
        inventoryRepository.deleteByRoom(room);
    }

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
        log.info("Updating all inventory by room with ID: {} between date range: {} - {}", roomId,
                updateInventoryRequestDTO.getStartDate(), updateInventoryRequestDTO.getEndDate());

        RoomEntity room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with Id: " + roomId));

        inventoryRepository.getInventoryAndLockBeforeUpdate(roomId, updateInventoryRequestDTO.getStartDate(),
                updateInventoryRequestDTO.getEndDate());

        inventoryRepository.updateInventory(roomId, updateInventoryRequestDTO.getStartDate(),
                updateInventoryRequestDTO.getEndDate(), updateInventoryRequestDTO.getClosed(),
                updateInventoryRequestDTO.getSurgeFactor());
    }
}
