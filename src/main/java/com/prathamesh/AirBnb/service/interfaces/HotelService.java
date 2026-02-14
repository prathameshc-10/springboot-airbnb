package com.prathamesh.AirBnb.service.interfaces;

import com.prathamesh.AirBnb.dto.HotelDTO;

import java.util.List;

public interface HotelService {
    HotelDTO createNewHotel(HotelDTO hotelDto);
    HotelDTO getHotelById(Long hotelId);
    HotelDTO updateHotelById(Long hotelId, HotelDTO hotelDTO);
    void deleteHotelById(Long hotelId);
    void activateHotel(Long hotelId);
    List<HotelDTO> getAllHotels();
}
