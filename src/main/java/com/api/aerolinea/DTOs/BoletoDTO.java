package com.api.aerolinea.DTOs;

import java.util.Date;
import java.util.UUID;

public record BoletoDTO(
        UUID id,
        String ciudadIda,
        String ciudadDestino,
        Date fechaIda,
        Date fechaCompra,
        Float precio
) {
}
