package com.api.aerolinea.Exceptions;

public class VueloNotFoundException extends RuntimeException{
    public VueloNotFoundException(String message) {
        super(message);
    }
}
