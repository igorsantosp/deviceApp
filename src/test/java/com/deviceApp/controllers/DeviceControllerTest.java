package com.deviceApp.controllers;

import com.deviceApp.entities.DeviceEntity;
import com.deviceApp.entities.StateEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.deviceApp.services.DeviceService;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

;
class DeviceControllerTest {
    @Mock
    private DeviceService deviceService;

    @InjectMocks
    private DeviceController deviceController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);}
    @Test
    void testGetDeviceById() throws Exception {
        DeviceEntity device = new DeviceEntity(1L, "Phone", "BrandX", StateEnum.AVAILABLE.toString(), LocalDateTime.now());
        when(deviceService.getDevice(1L)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(device));

        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
        mockMvc.perform(get("/device/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Phone"));
    }

    @Test
    void testgetDevicesByBrand() throws Exception {
        DeviceEntity device = new DeviceEntity(1L, "Phone", "BrandX", StateEnum.AVAILABLE.toString(), LocalDateTime.now());
        when(deviceService.getDevicesByBrand("BrandX")).thenReturn(ResponseEntity.status(HttpStatus.OK).body(List.of(device)));

        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
        mockMvc.perform(get("/device/brand/BrandX")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Phone"));
    }
    @Test
    void testGetDeviceByState() throws Exception {
        DeviceEntity device = new DeviceEntity(1L, "Phone", "BrandX", StateEnum.AVAILABLE.toString(), LocalDateTime.now());
        when(deviceService.getDevicesByState(StateEnum.AVAILABLE)).thenReturn(ResponseEntity.status(HttpStatus.OK).body(List.of(device)));

        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build();
        mockMvc.perform(get("/device/state/AVAILABLE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Phone"));
    }
}
