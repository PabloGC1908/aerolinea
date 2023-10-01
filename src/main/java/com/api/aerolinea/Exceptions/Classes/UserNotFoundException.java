package com.api.aerolinea.Exceptions.Classes;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
