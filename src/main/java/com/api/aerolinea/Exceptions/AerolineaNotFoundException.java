package com.api.aerolinea.Exceptions;

public class AerolineaNotFoundException extends RuntimeException {
    public AerolineaNotFoundException(String message) {
        super(message);
    }
}
