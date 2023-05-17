package com.sg.drones.service.impl;

import com.sg.drones.api.v1.dto.request.DroneCreateRequest;
import com.sg.drones.api.v1.dto.request.MedicationUploadRequest;
import com.sg.drones.domain.exception.ValidationException;
import com.sg.drones.domain.model.Medication;
import com.sg.drones.domain.repository.DroneRepository;
import com.sg.drones.domain.model.Drone;
import com.sg.drones.service.DroneService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class DroneServiceImpl implements DroneService {

    private final DroneRepository repository;

    public DroneServiceImpl(DroneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Drone register(DroneCreateRequest dto) {
        final Drone drone = dto.toEntity();
        final Drone persisted = repository.save(drone);
        return persisted;
    }

    @Override
    public Drone uploadMedication(final String serialNo,final List<MedicationUploadRequest> dto) {
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
            List<String> errors = new ArrayList<>();
            errors.add("Invalid Serial Number ");
            throw new ValidationException(errors);
        }
    }
}
