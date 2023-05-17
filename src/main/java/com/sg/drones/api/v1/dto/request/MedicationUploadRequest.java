package com.sg.drones.api.v1.dto.request;

import com.sg.drones.domain.model.Medication;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MedicationUploadRequest {

    @Pattern(regexp = "^[a-zA-Z\\-_]*$")
    private String name;

    private float weight;

    @Pattern(regexp = "^[A-Z0-9_]*$")
    private String code;
    private String imageUrl;

    public Medication toEntity() {
        final Medication medication = new Medication();
        medication.setWeight(weight);
        medication.setCode(code);
        medication.setName(name);
        medication.setImage(imageUrl);
        return medication;
    }

}
