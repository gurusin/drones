package com.sg.drones.service;

import com.sg.drones.api.v1.dto.request.DroneCreateRequest;
import com.sg.drones.api.v1.dto.request.MedicationUploadRequest;
import com.sg.drones.domain.model.Drone;

import java.util.List;

public interface DroneService {
    Drone register(DroneCreateRequest dto);

    Drone uploadMedication(String serialNo, List<MedicationUploadRequest> dto);

    Drone getBySerialNo(String serialNo);

    List<Drone> getDronesByState(String state);

    Object updateStatusOnBatteryCapacity(Drone c);
}
