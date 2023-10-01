package com.api.aerolinea.Exceptions.Classes;

public class AerolineaNotFoundException extends RuntimeException {
    public AerolineaNotFoundException(String message) {
        super(message);
    }
}
