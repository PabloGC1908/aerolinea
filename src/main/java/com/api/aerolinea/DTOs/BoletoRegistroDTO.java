package com.api.aerolinea.DTOs;

import java.util.UUID;

public record BoletoRegistroDTO(
        UUID usuarioId,
        UUID vueloId
) {
}
