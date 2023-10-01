package com.api.aerolinea.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.UUID;

public record TarjetaRegistroDTO (
        @NotNull(message = "La tarjeta debe estar asociado a un usuario")
        UUID idUsuario,
        @NotBlank(message = "El nombre de la tarjeta no puede ser nulo")
        String nombreTarjeta,
        @NotBlank
        @Pattern(regexp = "\\d{16}", message = "La tarjeta contiene un numero incorrecto")
        String numeroTarjeta,
        @NotNull(message = "La fecha no puede ser nula")
        String fechaExpiracion,
        @NotNull(message = "El cvv no puede ser nulo")
        @Pattern(regexp = "\\d{3}", message = "El cvv debe contener 3 digitos")
        Integer cvv
) {
}
