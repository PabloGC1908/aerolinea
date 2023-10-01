package com.api.aerolinea.Exceptions.Classes;

public class CiudadNotFoundException extends RuntimeException{
    public CiudadNotFoundException(String message) {
        super(message);
    }
}
