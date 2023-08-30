package com.api.aerolinea.Entities.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getUsuarios() {
        return ResponseEntity.ok(userService.getUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUsuario(@PathVariable UUID id) {
        return ResponseEntity.ok(userService.getUsuario(id));
    }

    @DeleteMapping("/{id}")
    public void deleteUsuario(@PathVariable UUID id) {
        userService.deleteUsuario(id);
    }

}
