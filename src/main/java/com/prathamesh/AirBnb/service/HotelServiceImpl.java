package com.prathamesh.AirBnb.service;

import com.prathamesh.AirBnb.dto.HotelDTO;
import com.prathamesh.AirBnb.entities.HotelEntity;
import com.prathamesh.AirBnb.exceptions.ResourceNotFoundException;
import com.prathamesh.AirBnb.repositories.HotelRepository;
import com.prathamesh.AirBnb.service.interfaces.HotelService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public HotelDTO updateHotelById(Long hotelId, HotelDTO hotelDTO) {
        log.info("Updating hotel with ID: {}", hotelId);
        HotelEntity hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        modelMapper.map(hotelDTO, hotel);
        hotel.setId(hotelId);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDTO.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long hotelId) {
        log.info("Deleting hotel with ID: {}", hotelId);
        HotelEntity hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        hotelRepository.deleteById(hotelId);
    }

    @Override
    @Transactional
    public void activateHotel(Long hotelId) {
        log.info("Activating the hotel with ID: {}", hotelId);
        HotelEntity hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        hotel.setActive(true);
    }

    @Override
    public List<HotelDTO> getAllHotels() {
        List<HotelEntity> hotels = hotelRepository.findAll();

        return hotels
                .stream()
                .map((element) -> modelMapper.map(element, HotelDTO.class))
                .collect(Collectors.toList());
    }
}
