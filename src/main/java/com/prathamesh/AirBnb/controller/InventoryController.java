package com.prathamesh.AirBnb.controller;

import com.prathamesh.AirBnb.dto.InventoryDTO;
import com.prathamesh.AirBnb.dto.UpdateInventoryRequestDTO;
import com.prathamesh.AirBnb.service.interfaces.InventoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/inventory")
@RequiredArgsConstructor
@Tag(name = "Admin inventory", description = "Manage hotel room inventory")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/rooms/{roomId}")
    @Operation(summary = "Retrieve inventory of a room",
            description = "Fetch all available inventory item for a given room ID",
            tags = {"Admin Inventory"})
    public ResponseEntity<List<InventoryDTO>> getAllInventoryByRoom(@PathVariable Long roomId){
        return ResponseEntity.ok(inventoryService.getAllInventoryByRoom(roomId));
    }

    @PatchMapping("/rooms/{roomId}")
    @Operation(summary = "Update inventory for a room",
            description = "Modify the inventory details for a specific room",
            tags = {"Admin Inventory"})
    public ResponseEntity<Void> updateInventory(@PathVariable Long roomId,
                                                @RequestBody UpdateInventoryRequestDTO updateInventoryRequestDTO){
        inventoryService.updateInventory(roomId, updateInventoryRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
