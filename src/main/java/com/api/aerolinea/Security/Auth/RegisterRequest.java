package com.api.aerolinea.Security.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RegisterRequest(
            @NotBlank(message = "Debe colocar sus nombres")
            String nombre,
            @NotBlank(message = "Debe colocar sus apellidos")
            String apellido,
            @Email(message = "Debe ingresar correctamente su email")
            String email,
            @NotNull
            @Pattern(regexp = "\\d{9}", message = "Ingrese correctamente su numero de telefono")
            String numero,
            @NotBlank(message = "Debe colocar una contraseña")
            String contrasenia
) {
}
