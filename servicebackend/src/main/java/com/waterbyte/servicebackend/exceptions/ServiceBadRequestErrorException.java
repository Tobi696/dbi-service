package com.waterbyte.servicebackend.exceptions;

public class ServiceBadRequestErrorException extends RuntimeException {
    public ServiceBadRequestErrorException(String message) {
        super(message);
    }
}
