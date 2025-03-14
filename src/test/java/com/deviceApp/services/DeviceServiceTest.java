package com.deviceApp.services;

import static org.junit.jupiter.api.Assertions.*;

import com.deviceApp.dtos.DeviceDTO;
import com.deviceApp.entities.DeviceEntity;
import com.deviceApp.entities.StateEnum;
import com.deviceApp.repositories.DeviceRepository;
import com.deviceApp.utils.ConvertionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
class DeviceServiceTest {


    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    private DeviceEntity device;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        device = new DeviceEntity(1L, "Phone", "BrandX", StateEnum.AVAILABLE.toString(), LocalDateTime.now());
    }

    @Test
    void testFindById_DeviceExists() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        DeviceEntity foundDevice = deviceService.getDevice(1L).getBody();
        assertNotNull(foundDevice);
        assertEquals("Phone", foundDevice.getName());
    }

    @Test
    void testFindById_DeviceNotFound() {
        when(deviceRepository.findById(1L)).thenReturn(Optional.empty());

        //assertThrows(RuntimeException.class, () -> deviceService.getDevice(1L));
        assertEquals(deviceService.getDevice(1L).getStatusCode().toString(), HttpStatus.NOT_FOUND.toString());
    }

    @Test
    void testSaveDevice() {
        when(deviceRepository.save(any(DeviceEntity.class))).thenReturn(device);

        DeviceEntity savedDevice = deviceService.createDevice(ConvertionUtil.getDto(device)).getBody();
        assertNotNull(savedDevice);
        assertEquals("Phone", savedDevice.getName());
    }

    @Test
    void testDeleteDevice() {
        doNothing().when(deviceRepository).delete(device);
        when(deviceRepository.findById(1L)).thenReturn(Optional.ofNullable(device));
        assertDoesNotThrow(() -> deviceService.deleteDevice(1L));
        verify(deviceRepository, times(1)).delete(device);
    }

}