package com.sg.drones.domain.exception;

import com.sg.drones.service.DroneService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Getter
public class ValidationException  extends RuntimeException{

    private static final Logger logger = LoggerFactory.getLogger(ValidationException.class);
    final List<String> validationErrors;

    public ValidationException(List<String> validationErrors) {
        logger.debug("Got validation errors :" +validationErrors);
        this.validationErrors = validationErrors;
    }
}
