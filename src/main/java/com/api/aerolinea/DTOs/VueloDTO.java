package com.api.aerolinea.DTOs;

import java.util.Date;
import java.util.UUID;

public record VueloDTO (
        UUID uuid,
        String aerolinea,
        String ciudadOrigen,
        String ciudadDestino,
        Integer cantidadPasajeros,
        Date fechaIda,
        Date fechaVuelta,
        Float precio

) {
}
