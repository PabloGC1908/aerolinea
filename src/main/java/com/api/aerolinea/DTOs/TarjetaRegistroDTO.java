package com.api.aerolinea.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record TarjetaRegistroDTO (
        @NotNull
        UUID idUsuario,
        @NotBlank
        String nombreTarjeta,
        @NotBlank
        @Pattern(regexp = "\\d{16}")
        String numeroTarjeta,
        @NotBlank
        String fechaExpiracion,
        @NotNull
        Integer cvv
) {
}
