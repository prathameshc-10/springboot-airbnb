package com.prathamesh.AirBnb.service;

import com.prathamesh.AirBnb.dto.RoomDTO;
import com.prathamesh.AirBnb.entities.HotelEntity;
import com.prathamesh.AirBnb.entities.RoomEntity;
import com.prathamesh.AirBnb.exceptions.ResourceNotFoundException;
import com.prathamesh.AirBnb.repositories.HotelRepository;
import com.prathamesh.AirBnb.repositories.InventoryRepository;
import com.prathamesh.AirBnb.repositories.RoomRepository;
import com.prathamesh.AirBnb.service.interfaces.InventoryService;
import com.prathamesh.AirBnb.service.interfaces.RoomService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final RoomRepository roomRepository;
    private final InventoryService inventoryService;

    @Override
    public RoomDTO createNewRoom(Long hotelId, RoomDTO roomDTO) {
        log.info("Creating a new room in hotel with ID: {}", hotelId);
        HotelEntity hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        RoomEntity room = modelMapper.map(roomDTO, RoomEntity.class);
        room.setHotel(hotel);
        roomRepository.save(room);

        if(hotel.getActive()){
            inventoryService.initializeRoomForAYear(room);
        }

        return modelMapper.map(room, RoomDTO.class);
    }

    @Override
    public List<RoomDTO> getAllRoomsInHotel(Long hotelId) {
        log.info("Get all rooms in a hotel with ID: {}", hotelId);
        HotelEntity hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        return hotel.getRooms()
                .stream()
                .map((element) -> modelMapper.map(element, RoomDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO getRoomById(Long roomId) {
        log.info("Getting room with ID: {}", roomId);
        RoomEntity room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        return modelMapper.map(room, RoomDTO.class);
    }

    @Override
    @Transactional
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room with ID: {}", roomId);
        RoomEntity room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        roomRepository.deleteById(roomId);
    }

    @Override
    public RoomDTO updateRoomById(Long hotelId, Long roomId, RoomDTO roomDTO) {
        log.info("Updating the room with ID: {}", roomId);
        HotelEntity hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        RoomEntity room = roomRepository
                .findById(roomId)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found with ID: " + roomId));

        modelMapper.map(roomDTO, room);
        room.setId(roomId);

        //If price of inventory updated then update the inventory for this room
        room = roomRepository.save(room);
        return modelMapper.map(room, RoomDTO.class);
    }
}
