package com.sg.drones.domain.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException  extends RuntimeException{

    final List<String> validationErrors;

    public ValidationException(List<String> validationErrors) {
        this.validationErrors = validationErrors;
    }
}
