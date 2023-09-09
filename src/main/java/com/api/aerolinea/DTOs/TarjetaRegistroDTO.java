package com.api.aerolinea.DTOs;

import java.util.UUID;

public record TarjetaRegistroDTO (
        UUID idUsuario,
        String nombreTarjeta,
        String numeroTarjeta,
        Integer mesExpiracion,
        Integer anioExpiracion,
        Integer cvv
) {
}
