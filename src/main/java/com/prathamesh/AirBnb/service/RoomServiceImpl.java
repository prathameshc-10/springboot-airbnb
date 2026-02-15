package com.prathamesh.AirBnb.service;

import com.prathamesh.AirBnb.dto.RoomDTO;
import com.prathamesh.AirBnb.entities.HotelEntity;
import com.prathamesh.AirBnb.entities.RoomEntity;
import com.prathamesh.AirBnb.exceptions.ResourceNotFoundException;
import com.prathamesh.AirBnb.repositories.HotelRepository;
import com.prathamesh.AirBnb.repositories.RoomRepository;
import com.prathamesh.AirBnb.service.interfaces.RoomService;
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

    @Override
    public RoomDTO createNewRoom(Long hotelId, RoomDTO roomDTO) {
        log.info("Creating a new room in hotel with ID: {}", hotelId);
        HotelEntity hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        RoomEntity room = modelMapper.map(roomDTO, RoomEntity.class);
        room.setHotel(hotel);
        roomRepository.save(room);
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
}
