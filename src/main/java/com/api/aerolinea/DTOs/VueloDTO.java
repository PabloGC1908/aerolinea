package com.api.aerolinea.DTOs;

import java.util.Date;

public record VueloDTO (
        String aerolinea,
        String ciudadOrigen,
        String ciudadDestino,
        Date fechaIda,
        Date fechaVuelta,
        Float precio
) {
}
