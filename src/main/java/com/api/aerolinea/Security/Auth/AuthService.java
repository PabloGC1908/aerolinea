package com.api.aerolinea.Security.Auth;

import com.api.aerolinea.Entities.Role;
import com.api.aerolinea.Entities.User;
import com.api.aerolinea.Exceptions.UserAlreadyExistsException;
import com.api.aerolinea.Exceptions.UserNotFoundException;
import com.api.aerolinea.Repositories.UserRepository;
import com.api.aerolinea.Security.Jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/**
 * Clase de servicio para autenticación y registro de usuarios.
 */
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /**
     * Constructor de AuthService que recibe las dependencias necesarias.
     *
     * @param userRepository          Repositorio de usuarios.
     * @param jwtService              Servicio JWT.
     * @param passwordEncoder         Codificador de contraseñas.
     * @param authenticationManager   Administrador de autenticación.
     */
    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Realiza el proceso de inicio de sesión de un usuario.
     *
     * @param request Solicitud de inicio de sesión.
     * @return Respuesta de autenticación que incluye un token JWT y los detalles del usuario.
     */
    public AuthResponse login(LoginRequest request) {
        // Autenticar al usuario
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.contrasenia()));

        if (!authentication.isAuthenticated()) {
            throw new UserNotFoundException("No se encontro al usuario");
        }

        // Obtener detalles del usuario basados en el correo electrónico
        UserDetails user = userRepository.findByEmail(request.email()).orElseThrow(
                () -> new UserNotFoundException("No se encontro al usuario"));

        // Obtener el perfil del usuario y almacenarlo en un array
        Object[] userProfile = userRepository.findUserProfileByEmail(request.email()).get(0);

        // Generar un token JWT para el usuario autenticado
        String token = jwtService.getToken(user);

        // Construir y devolver la respuesta de autenticación
        return AuthResponse.builder()
                .token(token)
                .nombre(String.valueOf(userProfile[0]))
                .apellido(String.valueOf(userProfile[1]))
                .role((Role) userProfile[2])
                .uuid((UUID) userProfile[3])
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Optional<User> userOptional = userRepository.findByEmail(request.email());

        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("El usuario ya se encuentra registrado.");
        }

        return registerUser(request);
    }


    /**
     * Realiza el proceso de registro de un nuevo usuario.
     *
     * @param request Solicitud de registro.
     * @return Respuesta de autenticación que incluye un token JWT y los detalles del usuario registrado.
     */
    private AuthResponse registerUser(RegisterRequest request) {
        // Crear una instancia de User con los datos proporcionados
        User user = User.builder()
                .uuid(UUID.randomUUID())
                .nombre(request.nombre())
                .apellido(request.apellido())
                .email(request.email())
                .numero(request.numero())
                .contrasenia(passwordEncoder.encode(request.contrasenia()))
                .role(Role.USER)
                .build();

        // Guardar el nuevo usuario en la base de datos
        userRepository.save(user);

        // Generar un token JWT para el usuario registrado
        String token = jwtService.getToken(user);

        // Construir y devolver la respuesta de autenticación
        return AuthResponse.builder()
                .token(token)
                .nombre(user.getNombre())
                .apellido(user.getApellido())
                .role(user.getRole())
                .build();
    }
}