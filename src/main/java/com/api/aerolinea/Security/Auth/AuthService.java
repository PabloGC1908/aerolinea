package com.api.aerolinea.Security.Auth;

import com.api.aerolinea.Security.Jwt.JwtService;
import com.api.aerolinea.Entities.User.Role;
import com.api.aerolinea.Entities.User.User;
import com.api.aerolinea.Entities.User.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.contrasenia()));
        UserDetails user = userRepository.findByEmail(request.email()).orElseThrow();
        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .uuid(UUID.randomUUID())
                .nombre(request.nombre())
                .apellido(request.apellido())
                .email(request.email())
                .numero(request.numero())
                .contrasenia(passwordEncoder.encode(request.contrasenia()))
                .role(Role.USER)
                .build();


        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }
}
