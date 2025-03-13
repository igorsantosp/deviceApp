package com.deviceApp.repositories;

import com.deviceApp.entities.DeviceEntity;
import com.deviceApp.entities.StateEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> findByBrand(String brand);
    List<DeviceEntity> findByState(StateEnum state);
}
