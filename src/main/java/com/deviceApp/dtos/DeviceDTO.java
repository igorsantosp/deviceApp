package com.deviceApp.dtos;

import com.deviceApp.entities.DeviceEntity;
import com.deviceApp.entities.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeviceDTO {
    private String name;
    private String brand;
    private StateEnum state;

    public DeviceEntity getEntity(){
        DeviceEntity entity = new DeviceEntity();
        entity.setBrand(this.getBrand());
        entity.setName(this.getName());
        entity.setState(this.getState().toString());
        return entity;
    }
}
