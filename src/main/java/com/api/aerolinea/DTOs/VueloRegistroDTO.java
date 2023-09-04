package com.api.aerolinea.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VueloRegistroDTO(
        @NotNull
        Integer aerolineaId,
        @NotNull
        Integer cantidadPasajeros,
        @NotNull
        Integer ciudadDestinoId,
        @NotNull
        Integer ciudadOrigenId,
        @NotBlank
        Float precio,
        @NotNull
        String fechaIda,
        @NotNull
        String fechaVuelta
) {
}
