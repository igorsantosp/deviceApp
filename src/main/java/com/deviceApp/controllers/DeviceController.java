package com.deviceApp.controllers;

import com.deviceApp.dtos.DeviceDTO;
import com.deviceApp.entities.DeviceEntity;
import com.deviceApp.entities.StateEnum;
import com.deviceApp.services.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @PostMapping
    public ResponseEntity<DeviceEntity> createDevice(@RequestBody DeviceDTO deviceDTO) {
        return deviceService.createDevice(deviceDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceEntity> getDevice(@PathVariable Long id) {
        return deviceService.getDevice(id);
    }

    @GetMapping
    public ResponseEntity<List<DeviceEntity>> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<DeviceEntity>> getDevicesByBrand(@PathVariable String brand) {
        return deviceService.getDevicesByBrand(brand);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<DeviceEntity>> getDevicesByState(@PathVariable StateEnum state) {
        return deviceService.getDevicesByState(state);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDevice(@PathVariable Long id, @RequestBody DeviceDTO deviceDTO) {
        return deviceService.updateDevice(id, deviceDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Long id) {
        return deviceService.deleteDevice(id);
    }
}
