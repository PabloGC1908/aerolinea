package com.api.aerolinea.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDTO(
        @NotBlank
        String nombre,
        @NotBlank
        String apellido,
        @Email
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{9}")
        String numero,
        @NotBlank
        @NotNull
        String contrasenia
) {
}
