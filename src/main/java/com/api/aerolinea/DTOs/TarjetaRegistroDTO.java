package com.api.aerolinea.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TarjetaRegistroDTO (
        @NotNull
        UUID idUsuario,
        @NotBlank
        String nombreTarjeta,
        @NotBlank
        String numeroTarjeta,
        @NotBlank
        String fechaExpiracion,
        @NotNull
        Integer cvv
) {
}
