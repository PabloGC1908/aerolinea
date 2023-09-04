package com.api.aerolinea.Controllers;

import com.api.aerolinea.DTOs.UserDTO;
import com.api.aerolinea.Entities.User;
import com.api.aerolinea.Services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getUsuarios() {
        return userService.getUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUsuario(@PathVariable UUID id) {
        return userService.getUsuario(id);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> postUsuario(@Valid @RequestBody UserDTO user) {
        return userService.postUsuario(user);
    }

    @PatchMapping("/{id}")
    @Transactional
    public ResponseEntity<String> patchUsuario(@Valid @RequestBody UserDTO user, @PathVariable UUID id) {
        return userService.patchUsuario(user, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable UUID id) {
        userService.deleteUsuario(id);
    }

}
