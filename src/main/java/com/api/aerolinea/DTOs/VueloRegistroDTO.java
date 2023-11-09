package com.api.aerolinea.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VueloRegistroDTO(
        @NotNull
        Integer aerolineaId,
        @NotNull
        @Min(message = "La cantidad de pasajeros no puede ser negativo", value = 0)
        Integer cantidadPasajeros,
        @NotNull
        Integer ciudadDestinoId,
        @NotNull
        Integer ciudadOrigenId,
        @NotNull(message = "El precio no debe ser nulo")
        @Min(message = "El precio no puede ser negativo", value = 0L)
        Float precio,
        @NotNull(message = "La fecha de ida no puede ser nula")
        String fechaIda,
        @NotNull(message = "La fecha de vuelta no puede ser nula")
        String fechaVuelta
) {
}
