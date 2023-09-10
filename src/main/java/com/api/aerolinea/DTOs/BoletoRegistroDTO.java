package com.api.aerolinea.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BoletoRegistroDTO(
        @NotNull
        @NotBlank
        UUID usuarioId,
        @NotNull
        @NotBlank
        UUID vueloId
) {
}
