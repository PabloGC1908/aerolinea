package com.api.aerolinea.Config;

import com.api.aerolinea.Security.Jwt.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Indica que esta clase es una configuración de Spring.
@EnableWebSecurity // Habilita la configuración de seguridad web de Spring.
@EnableMethodSecurity
public class SecurityConfig  {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    /**
     * Constructor de SecurityConfig que inyecta JwtAuthenticationFilter y AuthenticationProvider.
     *
     * @param jwtAuthenticationFilter El filtro de autenticación JWT personalizado.
     * @param authenticationProvider El proveedor de autenticación personalizado.
     */
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationProvider authenticationProvider) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    /**
     * Configura la cadena de filtros de seguridad de Spring.
     *
     * @param http El objeto HttpSecurity que se configura para establecer políticas de seguridad.
     * @return La cadena de filtros de seguridad configurada.
     * @throws Exception Si hay excepciones al configurar la seguridad.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Deshabilita la protección CSRF.
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/**").permitAll() // Permite todas las solicitudes para cualquier URL.
                                .anyRequest().authenticated() // Requiere autenticación para todas las demás solicitudes.
                        )
                .sessionManagement(sessionManager ->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Configura la administración de
                                                                                        // sesiones como sin estado.
                        )
                .authenticationProvider(authenticationProvider) // Establece el proveedor de autenticación.
                // Agrega un filtro personalizado antes del filtro de autenticación por nombre de usuario y contraseña.
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build(); // Construye y devuelve la cadena de filtros de seguridad.
    }
}
