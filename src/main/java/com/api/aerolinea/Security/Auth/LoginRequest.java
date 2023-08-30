package com.api.aerolinea.Security.Auth;

public record LoginRequest(
    String email,
    String contrasenia
) {
}
