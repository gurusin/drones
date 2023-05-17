package com.sg.drones.domain.repository;

import com.sg.drones.domain.model.Drone;
import com.sg.drones.domain.model.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {

    Optional<Drone> getDroneBySerialNo(final String serialNo);
    List<Drone> getDronesByState(DroneState state);
}
