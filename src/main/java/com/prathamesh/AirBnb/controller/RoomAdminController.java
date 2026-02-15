package com.prathamesh.AirBnb.controller;

import com.prathamesh.AirBnb.dto.RoomDTO;
import com.prathamesh.AirBnb.service.interfaces.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
@Tag(name = "Room Admin Management", description = "Admin manages rooms/hotels/inventories in a hotel")
public class RoomAdminController {
    private final RoomService roomService;

    @PostMapping
    @Operation(summary = "Creates a new room",
            description = "Adds a new room to a specific hotel")
    public ResponseEntity<RoomDTO> createNewRoom(@PathVariable Long hotelId, @RequestBody RoomDTO roomDTO){
        RoomDTO room = roomService.createNewRoom(hotelId, roomDTO);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retrieve all rooms in hotel",
            description = "Fetches all room belonging to a specific hotel")
    public ResponseEntity<List<RoomDTO>> getAllRoomsInHotel(@PathVariable Long hotelId){
        return ResponseEntity.ok(roomService.getAllRoomsInHotel(hotelId));
    }
}
