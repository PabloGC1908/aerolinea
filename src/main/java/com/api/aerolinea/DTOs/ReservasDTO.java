package com.api.aerolinea.DTOs;

import java.util.Date;
import java.util.UUID;

public record ReservasDTO(
        UUID id,
        String nombre,
        String apellido,
        Date fechaIda,
        Date fechaCompra,
        Float precio
) {
}
