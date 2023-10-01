package com.api.aerolinea.Exceptions.Classes;

public class VueloNotFoundException extends RuntimeException{
    public VueloNotFoundException(String message) {
        super(message);
    }
}
