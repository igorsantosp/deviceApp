package com.deviceApp.entities;

import com.deviceApp.dtos.DeviceDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEVICE")
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StateEnum state;

    @Column(nullable = false, updatable = false)
    private LocalDateTime creationTime;


   public DeviceDTO getDto(){
       return new DeviceDTO(this.getName(),this.getBrand(),this.getState());
   }
}
