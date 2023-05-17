package com.sg.drones.domain.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Medication extends BaseEntity
{
    private String name;
    private float weight;
    private String code;
    //URL of the image is stored
    private String image;
}
