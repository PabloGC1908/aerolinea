package com.api.aerolinea.Exceptions;

public class CiudadNotFoundException extends RuntimeException{
    public CiudadNotFoundException(String message) {
        super(message);
    }
}
