package com.prathamesh.AirBnb.service.interfaces;

import com.prathamesh.AirBnb.dto.RoomDTO;

import java.util.List;

public interface RoomService {
    RoomDTO createNewRoom(Long hotelId, RoomDTO roomDTO);
    List<RoomDTO> getAllRoomsInHotel(Long hotelId);
}
