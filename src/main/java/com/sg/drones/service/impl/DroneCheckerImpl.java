package com.sg.drones.service.impl;

import com.sg.drones.domain.model.Drone;
import com.sg.drones.domain.model.DroneState;
import com.sg.drones.domain.repository.DroneRepository;
import com.sg.drones.service.DroneService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DroneCheckerImpl {

    private final DroneRepository repository;
    private final DroneService droneService;

    public DroneCheckerImpl(DroneRepository repository, DroneService service) {
        this.repository = repository;
        this.droneService = service;
    }

    @Scheduled(cron = "${check.battery.interval}")
    public void check(){
        List<Drone> droneList = repository.getDronesByState(DroneState.LOADING);
        droneList.parallelStream().map(c ->droneService.updateStatusOnBatteryCapacity(c));
    }
}
