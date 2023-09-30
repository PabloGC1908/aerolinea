package com.api.aerolinea.Security.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        log.info("Solicitud para inicio de sesion: {}", request.email());
        return ResponseEntity.ok().body(authService.login(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        log.info("Registrando usuario: {}", request.email());
        return ResponseEntity.ok(authService.register(request));
    }
}
