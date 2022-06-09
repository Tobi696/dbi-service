package com.waterbyte.servicebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmployeeBadRequestErrorException extends RuntimeException {
    public EmployeeBadRequestErrorException(String message) {
        super(message);
    }
}
