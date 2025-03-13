package com.deviceApp.utils;

import com.deviceApp.dtos.DeviceDTO;
import com.deviceApp.entities.DeviceEntity;
import com.deviceApp.entities.StateEnum;

public class ConvertionUtil {
    static public DeviceDTO getDto(DeviceEntity entity){
        return new DeviceDTO(entity.getName(),entity.getBrand(), StateEnum.valueOf(entity.getState()));
    }
}
