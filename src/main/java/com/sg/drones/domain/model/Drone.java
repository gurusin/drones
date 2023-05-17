package com.sg.drones.domain.model;

import com.sg.drones.domain.exception.ValidationException;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Drone extends BaseEntity {

    @Column(unique = true)
    private String serialNo;
    private DroneModel model;
    private float maxWeight;
    private float batteryCapacity;
    private DroneState state;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "drone_id")
    private List<Medication> medicationList;

    public void validate() {
        List<String> errors = new ArrayList<>();

        if(serialNo == null || serialNo.length() > 100){
            errors.add("Serial Number should have only 100 chars");
        }
        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }

    public void addMedications(List<Medication> medications) {
        medicationList.addAll(medications);
    }

    public void validateMedications(){
        List<String> errors = new ArrayList<>();
        final double sum = medicationList.stream().mapToDouble(e -> e.getWeight())
                .sum();

        if(DroneState.IDLE != state || DroneState.LOADING != state){
            errors.add("Drone is not ready for loading ");
        }
        
        if(sum > maxWeight){
            errors.add("Loaded Medication exceed the max weight of " + sum);
        }else if(sum == maxWeight){
            state = DroneState.LOADED;
        }else {
            state = DroneState.LOADING;
        }

        if(!errors.isEmpty()){
            throw new ValidationException(errors);
        }
    }
}
