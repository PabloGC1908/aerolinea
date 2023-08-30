package com.api.aerolinea.Entities.Aerolinea;

import jakarta.validation.constraints.NotBlank;

public record AerolineaDTO(
        @NotBlank
        String aerolinea
) {
}
