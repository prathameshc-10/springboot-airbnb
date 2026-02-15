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

    @GetMapping("/{roomId}")
    @Operation(summary = "Get details of a specific room",
            description = "Fetches details of specific room in hotel by ID")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long hotelId, @PathVariable Long roomId){
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }

    @DeleteMapping("/{roomId}")
    @Operation(summary = "Delete a room",
            description = "Delete a room from the hotel By ID")
    public ResponseEntity<Void> deleteRoomById(@PathVariable Long hotelId, @PathVariable Long roomId){
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{roomId}")
    @Operation(summary = "Update a room",
            description = "Update details of a existing room")
    public ResponseEntity<RoomDTO> updateRoomById(@PathVariable Long hotelId, @PathVariable Long roomId,
                                                  @RequestBody RoomDTO roomDTO){
        return ResponseEntity.ok(roomService.updateRoomById(hotelId, roomId, roomDTO));
    }
}
