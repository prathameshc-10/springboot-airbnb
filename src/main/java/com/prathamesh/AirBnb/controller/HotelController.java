package com.prathamesh.AirBnb.controller;

import com.prathamesh.AirBnb.dto.HotelDTO;
import com.prathamesh.AirBnb.service.interfaces.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Hotel Management", description = "Manage hotel details")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    @PostMapping
    @Operation(summary = "Create a new hotel", description = "Adds a new hotel to the system")
    public ResponseEntity<HotelDTO> createNewHotel(@RequestBody HotelDTO hotelDTO){
        log.info("Attempting to create a new hotel with name: {}", hotelDTO.getName());
        HotelDTO hotel = hotelService.createNewHotel(hotelDTO);
        return new ResponseEntity<>(hotel, HttpStatus.CREATED);
    }

    @GetMapping("/{hotelId}")
    @Operation(summary = "Get hotel by ID", description = "Fetch detail of specific hotel")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long hotelId){
        HotelDTO hotelDTO = hotelService.getHotelById(hotelId);
        return ResponseEntity.ok(hotelDTO);
    }

    @PutMapping("/{hotelId}")
    @Operation(summary = "Update hotel details", description = "Modify hotel with information")
    public ResponseEntity<HotelDTO> updateHotelById(@PathVariable Long hotelId, @RequestBody HotelDTO hotelDTO){
        log.info("Attempting to update hotel info with ID: {}", hotelId);
        HotelDTO hotel = hotelService.updateHotelById(hotelId, hotelDTO);
        return ResponseEntity.ok(hotel);
    }

    @DeleteMapping("/{hotelId}")
    @Operation(summary = "Delete a hotel", description = "Removes a hotel from the system")
    public ResponseEntity<Void> deleteHotelById(@PathVariable Long hotelId){
        hotelService.deleteHotelById(hotelId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{hotelId}/activate")
    @Operation(summary = "Activate a hotel", description = "Makes a hotel as active")
    public ResponseEntity<Void> activateHotel(@PathVariable Long hotelId){
        hotelService.activateHotel(hotelId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @Operation(summary = "Get all hotels owned by admin",
            description = "Retrieve the list of all hotels owned by admin")
    public ResponseEntity<List<HotelDTO>> getAllHotels(){
        return ResponseEntity.ok(hotelService.getAllHotels());
    }
}
