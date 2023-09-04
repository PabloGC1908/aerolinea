package com.api.aerolinea.Security.Auth;


import com.api.aerolinea.Entities.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    String nombre;
    String apellido;
    Role role;
}
