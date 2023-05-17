package com.sg.drones.api.v1.dto.request;

import com.sg.drones.domain.model.DroneState;
import com.sg.drones.domain.model.Drone;
import com.sg.drones.domain.model.DroneModel;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DroneCreateRequest {

    @NotBlank
    @Size(max=100)
    private String serialNo;

    @NotNull
    private DroneModel model;

    @DecimalMax(value = "500", inclusive = false)
    private float weightLimit;

    @DecimalMax(value = "100", inclusive = false)
    private float batteryCapacity;


    public Drone toEntity(){
        final Drone drone = new Drone();
        drone.setModel(model);
        drone.setBatteryCapacity(batteryCapacity);
        drone.setState(DroneState.IDLE);
        drone.setSerialNo(serialNo);
        drone.setMaxWeight(weightLimit);
        drone.validateBatteryCapacity();
        return drone;
    }
}
