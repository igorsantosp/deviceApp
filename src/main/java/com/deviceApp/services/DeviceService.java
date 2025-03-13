package com.deviceApp.services;

import com.deviceApp.dtos.DeviceDTO;
import com.deviceApp.entities.DeviceEntity;
import com.deviceApp.entities.StateEnum;
import com.deviceApp.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
private DeviceRepository deviceRepository;
    public ResponseEntity<DeviceEntity> createDevice(DeviceDTO deviceDTO) {
        DeviceEntity entity = deviceDTO.getEntity();
        entity.setCreationTime(LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.CREATED).body(deviceRepository.save(entity));
    }

    public ResponseEntity<DeviceEntity> getDevice(Long id) {
        return deviceRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<List<DeviceEntity>> getAllDevices() {
        List<DeviceEntity> devices = deviceRepository.findAll();
        return ResponseEntity.ok(devices);
    }

    public ResponseEntity<List<DeviceEntity>> getDevicesByBrand(String brand) {
        List<DeviceEntity> devices = deviceRepository.findByBrand(brand);
        return ResponseEntity.ok(devices);
    }

    public ResponseEntity<List<DeviceEntity>> getDevicesByState(StateEnum state) {
        List<DeviceEntity> devices = deviceRepository.findByState(state);
        return ResponseEntity.ok(devices);
    }


    public ResponseEntity<?> updateDevice(Long id, DeviceDTO deviceDTO) {
        return deviceRepository.findById(id)
                .map(device -> {
                    if (StateEnum.valueOf(device.getState()) == StateEnum.IN_USE) {
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(null); // 409 Conflict: Cannot modify a device in use
                    }
                    device.setState(deviceDTO.getState().toString());
                    DeviceEntity updatedDevice = deviceRepository.save(device);
                    return ResponseEntity.ok(updatedDevice);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Void> deleteDevice(Long id) {
        Optional<DeviceEntity> deviceOpt = deviceRepository.findById(id);

        if (deviceOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        DeviceEntity device = deviceOpt.get();
        if (StateEnum.valueOf(device.getState()) == StateEnum.IN_USE) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        deviceRepository.delete(device);
        return ResponseEntity.noContent().build();
    }
}
