package com.waterbyte.servicebackend.exceptions;

public class ServiceNotFoundErrorException extends RuntimeException {
    public ServiceNotFoundErrorException(String message) {
        super(message);
    }
}
