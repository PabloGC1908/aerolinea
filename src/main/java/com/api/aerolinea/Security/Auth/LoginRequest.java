package com.api.aerolinea.Security.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Email(message = "Ingrese su correo correctamente")
        String email,
        @NotBlank(message = "Ingrese su contraseña")
        String contrasenia
) {
}
