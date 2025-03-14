package com.deviceApp.controllers;

import com.deviceApp.dtos.DeviceDTO;
import com.deviceApp.entities.DeviceEntity;
import com.deviceApp.entities.StateEnum;
import com.deviceApp.services.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/device")
@Tag(name = "Device API", description = "Endpoints for managing devices")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @Operation(summary = "Create a device", description = "Create a device according with the body data.")
    @PostMapping
    public ResponseEntity<DeviceEntity> createDevice(@RequestBody DeviceDTO deviceDTO) {
        return deviceService.createDevice(deviceDTO);
    }
    @Operation(summary = "Get a single devices", description = "Retrieves a device according with his ID.")
    @GetMapping("/{id}")
    public ResponseEntity<DeviceEntity> getDevice(@PathVariable Long id) {
        return deviceService.getDevice(id);
    }

    @Operation(summary = "Get all devices", description = "Retrieves a list of all available devices.")
    @GetMapping
    public ResponseEntity<List<DeviceEntity>> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @Operation(summary = "Get all devices by brand", description = "Retrieves a list of all available devices using the brand as filter")
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<DeviceEntity>> getDevicesByBrand(@PathVariable String brand) {
        return deviceService.getDevicesByBrand(brand);
    }

    @Operation(summary = "Get all devices by state", description = "Retrieves a list of all available devices using the state as filter")
    @GetMapping("/state/{state}")
    public ResponseEntity<List<DeviceEntity>> getDevicesByState(@PathVariable StateEnum state) {
        return deviceService.getDevicesByState(state);
    }


    @Operation(summary = "Update a device by id", description = "update a device using its ID and body information")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDevice(@PathVariable Long id, @RequestBody DeviceDTO deviceDTO) {
        return deviceService.updateDevice(id, deviceDTO);
    }


    @Operation(summary = "Delete a device by its id", description = "deletes a single device using its id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        return deviceService.deleteDevice(id);

    }
}
