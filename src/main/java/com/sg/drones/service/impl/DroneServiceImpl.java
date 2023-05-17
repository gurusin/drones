package com.sg.drones.service.impl;

import com.sg.drones.api.v1.dto.request.DroneCreateRequest;
import com.sg.drones.api.v1.dto.request.MedicationUploadRequest;
import com.sg.drones.domain.exception.ValidationException;
import com.sg.drones.domain.model.DroneState;
import com.sg.drones.domain.model.Medication;
import com.sg.drones.domain.repository.DroneRepository;
import com.sg.drones.domain.model.Drone;
import com.sg.drones.service.DroneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {

    private static final Logger logger = LoggerFactory.getLogger(DroneService.class.getName());

    private final DroneRepository repository;

    public DroneServiceImpl(DroneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Drone register(DroneCreateRequest dto) {
        logger.info(" Registering drone :" + dto.getSerialNo());
        final Drone drone = dto.toEntity();
        final Drone persisted = repository.save(drone);
        return persisted;
    }

    @Override
    public Drone uploadMedication(final String serialNo,final List<MedicationUploadRequest> dto) {
        logger.info(" Uploading Medication to  :" + serialNo);
        final Optional<Drone> drone = repository.getDroneBySerialNo(serialNo);
        if(drone.isPresent()){
            List<Medication> medications = dto.parallelStream().
                    map(c -> c.toEntity()).
                    collect(Collectors.toList());
            final Drone entity = drone.get();
            entity.addMedications(medications);
            entity.validateMedications();
            return  repository.save(entity);
        }else {
            logger.debug("Validation errors during uploading medication to :" + serialNo);
            List<String> errors = new ArrayList<>();
            errors.add("Invalid Serial Number ");
            throw new ValidationException(errors);
        }
    }

    @Override
    public Drone getBySerialNo(String serialNo) {
        final Optional<Drone> drone = repository.getDroneBySerialNo(serialNo);
        if(drone.isPresent()){
           return drone.get();
        }else {
            List<String> errors = new ArrayList<>();
            errors.add("Invalid Serial Number ");
            throw new ValidationException(errors);
        }
    }

    @Override
    public List<Drone> getDronesByState(String state) {
        if(state != null){
            DroneState eState = DroneState.valueOf(state);
            return repository.getDronesByState(eState);
        }
        return repository.findAll();
    }

    @Override
    public Drone updateStatusOnBatteryCapacity(Drone c) {
        c.validateBatteryCapacity();
        return repository.save(c);
    }

}
