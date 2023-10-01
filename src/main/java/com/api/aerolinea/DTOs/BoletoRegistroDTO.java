package com.api.aerolinea.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BoletoRegistroDTO(
        @NotNull(message = "La boleta debe estar asociada a un usuario")
        @NotBlank
        UUID usuarioId,
        @NotNull(message = "La boleta debe estar asociado a un vuelo")
        @NotBlank
        UUID vueloId
) {
}
