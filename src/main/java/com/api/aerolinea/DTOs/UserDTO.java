package com.api.aerolinea.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserDTO(
        @NotBlank
        @NotNull
        String nombre,
        @NotBlank
        @NotNull
        String apellido,
        @Email
        @NotNull
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{9}")
        String numero,
        @NotBlank
        @NotNull
        String contrasenia
) {
}
