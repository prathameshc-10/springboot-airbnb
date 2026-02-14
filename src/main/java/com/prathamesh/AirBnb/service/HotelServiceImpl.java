package com.prathamesh.AirBnb.service;

import com.prathamesh.AirBnb.dto.HotelDTO;
import com.prathamesh.AirBnb.entities.HotelEntity;
import com.prathamesh.AirBnb.exceptions.ResourceNotFoundException;
import com.prathamesh.AirBnb.repositories.HotelRepository;
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
    private final HotelRepository hotelRepository;

    @Override
    public HotelDTO createNewHotel(HotelDTO hotelDto) {
        log.info("Crating a new Hotel with name: {} ", hotelDto.getName());
        HotelEntity hotel = modelMapper.map(hotelDto, HotelEntity.class);
        hotel.setActive(false);

        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with ID: {}", hotel.getId());
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    public HotelDTO getHotelById(Long hotelId) {
        log.info("Getting the hotel with id: {}", hotelId);
        HotelEntity hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not Found with id: " + hotelId));

        return modelMapper.map(hotel, HotelDTO.class);
    }
}
