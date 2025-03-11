package com.deviceApp.dtos;

import com.deviceApp.entities.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceDTO {
    private String name;
    private String brand;
    private StateEnum state;
}
