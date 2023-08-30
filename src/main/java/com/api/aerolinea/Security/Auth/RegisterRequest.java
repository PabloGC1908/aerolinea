package com.api.aerolinea.Security.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterRequest(
            @NotBlank
            String nombre,
            @NotBlank
            String apellido,
            @Email
            String email,
            @NotNull
            @Pattern(regexp = "\\d{9}")
            String numero,
            @NotBlank
            String contrasenia
) {
}
