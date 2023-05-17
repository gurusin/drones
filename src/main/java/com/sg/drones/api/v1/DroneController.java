package com.sg.drones.api.v1;

import com.sg.drones.api.v1.dto.request.DroneCreateRequest;
import com.sg.drones.api.v1.dto.request.MedicationUploadRequest;
import com.sg.drones.domain.model.Drone;
import com.sg.drones.service.DroneService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    private final DroneService droneService;

    public DroneController(DroneService droneService) {
        this.droneService = droneService;
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody DroneCreateRequest dto, BindingResult bindingResult){

        final StringBuilder builder = validateBindingResult(bindingResult);
        if (builder.isEmpty()){
            final Drone drone  = droneService.register(dto);
            return new ResponseEntity(drone, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(builder.toString());

    }

    @PostMapping("/{sno}")
    public ResponseEntity loadMedication(@Valid @RequestBody List<MedicationUploadRequest> dto, BindingResult bindingResult,
                                         @PathVariable("sno") String serialNo){

        final StringBuilder builder = validateBindingResult(bindingResult);
        if (builder.isEmpty()){
            final Drone drone  = droneService.uploadMedication(serialNo,dto);
            return new ResponseEntity(drone, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(builder.toString());

    }

    @GetMapping("/{sno}")
    public ResponseEntity getDrone(@PathVariable("sno") String serialNo){
        final Drone drone =droneService.getBySerialNo(serialNo);
        return new ResponseEntity(drone, HttpStatus.OK);

    }
    private StringBuilder validateBindingResult(final BindingResult bindingResult){
        final  StringBuilder errors = new StringBuilder();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> errors.append(error.getField()+" "
                    +error.getDefaultMessage()).append("\n"));
        }
        return errors;
    }



}
