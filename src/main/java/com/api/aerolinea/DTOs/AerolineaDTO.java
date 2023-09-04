package com.api.aerolinea.DTOs;

import jakarta.validation.constraints.NotBlank;

public record AerolineaDTO(
        @NotBlank
        String aerolinea
) {
}
