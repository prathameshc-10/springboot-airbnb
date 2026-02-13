package com.prathamesh.AirBnb.service;

import com.prathamesh.AirBnb.dto.HotelDTO;
import com.prathamesh.AirBnb.entities.HotelEntity;
import com.prathamesh.AirBnb.service.interfaces.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private final ModelMapper modelMapper;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDto) {
        log.info("Crating a new Hotel with name: {} ", hotelDto.getName());
        HotelEntity hotel = modelMapper.map(hotelDto, HotelEntity.class);
        return null;
    }
}
